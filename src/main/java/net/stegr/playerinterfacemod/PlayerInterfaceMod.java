package net.stegr.playerinterfacemod;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.stegr.playerinterfacemod.handler.ConfigurationHandler;
import net.stegr.playerinterfacemod.handler.PlimEventHandler;
import net.stegr.playerinterfacemod.init.ModBlocks;
import net.stegr.playerinterfacemod.init.ModItems;
import net.stegr.playerinterfacemod.init.ModTileEntities;
import net.stegr.playerinterfacemod.integration.ModRegistry;
import net.stegr.playerinterfacemod.network.PacketHandler;
import net.stegr.playerinterfacemod.proxy.CommonProxy;
import net.stegr.playerinterfacemod.recipe.RecipeRegistry;
import net.stegr.playerinterfacemod.reference.MachineNames;
import net.stegr.playerinterfacemod.reference.Reference;
import net.stegr.playerinterfacemod.reference.UpgradeNames;
import net.stegr.playerinterfacemod.registry.UpgradeRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME,version = Reference.VERSION, dependencies = "", acceptableRemoteVersions = Reference.VERSION)
public class PlayerInterfaceMod
{
    @Mod.Instance(Reference.MOD_ID)
    public static PlayerInterfaceMod instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide =  Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModTileEntities.init();
        ModBlocks.init();
        ModItems.init();

        PacketHandler.init();

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        UpgradeRegistry.instance().registerUpgrade(ModItems.ItemTransferUpgrade);
        //UpgradeRegistry.instance().registerUpgrade(ModItems.ItemBufferUpgrade);
        UpgradeRegistry.instance().registerUpgrade(ModItems.RFTransferUpgrade);
        UpgradeRegistry.instance().registerUpgrade(ModItems.ComperatorUpgrade);

        UpgradeRegistry.instance().registerMachine(ModBlocks.player_interface, MachineNames.PLAYER_INTERFACE);

        UpgradeRegistry.instance().addValidUpgradeToMachine(MachineNames.PLAYER_INTERFACE, UpgradeRegistry.instance().getUpgrade(UpgradeNames.ITEMTRANSFER), 1);
        UpgradeRegistry.instance().addValidUpgradeToMachine(MachineNames.PLAYER_INTERFACE, UpgradeRegistry.instance().getUpgrade(UpgradeNames.RFTRANSFER), 1);
        UpgradeRegistry.instance().addValidUpgradeToMachine(MachineNames.PLAYER_INTERFACE, UpgradeRegistry.instance().getUpgrade(UpgradeNames.COMPERATOR), 1);

        proxy.registerRenderers();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ModRegistry.init();

        RecipeRegistry.init();
        RecipeRegistry.loadRecipes(ConfigurationHandler.recipesType);

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
        MinecraftForge.EVENT_BUS.register(new PlimEventHandler());
        FMLCommonHandler.instance().bus().register(new PlimEventHandler());
    }
}
