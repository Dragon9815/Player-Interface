package net.dragon9815.playerinterfacemod;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.dragon9815.dragoncore.registry.UpgradeRegistry;
import net.dragon9815.playerinterfacemod.handler.GuiEventHandler;
import net.dragon9815.playerinterfacemod.handler.KeyEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.dragon9815.playerinterfacemod.handler.ConfigurationHandler;
import net.dragon9815.playerinterfacemod.handler.PlimEventHandler;
import net.dragon9815.playerinterfacemod.helpers.LogHelper;
import net.dragon9815.playerinterfacemod.init.ModBlocks;
import net.dragon9815.playerinterfacemod.init.ModItems;
import net.dragon9815.playerinterfacemod.init.ModTileEntities;
import net.dragon9815.playerinterfacemod.integration.ModRegistry;
import net.dragon9815.playerinterfacemod.network.PacketHandler;
import net.dragon9815.playerinterfacemod.proxy.CommonProxy;
import net.dragon9815.playerinterfacemod.recipe.RecipeRegistry;
import net.dragon9815.playerinterfacemod.reference.ModIDs;
import net.dragon9815.playerinterfacemod.reference.Reference;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = "required-after:DragonCore@[1.0,);after:TConstruct;after:Waila", acceptableRemoteVersions = Reference.VERSION)
public class PlayerInterfaceMod {
    @Mod.Instance(Reference.MOD_ID)
    public static PlayerInterfaceMod instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModTileEntities.init();
        ModBlocks.init();
        ModItems.init();

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        PacketHandler.init();

        proxy.registerClientStuff();
        proxy.registerServerStuff();
        proxy.registerRenderers();

        UpgradeRegistry.instance().registerUpgrade(ModItems.ItemTransferUpgrade);
        UpgradeRegistry.instance().registerUpgrade(ModItems.RFTransferUpgrade);
        UpgradeRegistry.instance().registerUpgrade(ModItems.ComperatorUpgrade);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        RecipeRegistry.init();
        RecipeRegistry.loadRecipes(ConfigurationHandler.recipesType);

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
        FMLCommonHandler.instance().bus().register(new KeyEventHandler());
        MinecraftForge.EVENT_BUS.register(new PlimEventHandler());
        MinecraftForge.EVENT_BUS.register(new GuiEventHandler());
        FMLCommonHandler.instance().bus().register(new PlimEventHandler());
    }

    @EventHandler
    public void postInit(FMLInitializationEvent event) {
        ModRegistry.init();
    }
}
