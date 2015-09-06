package net.stegr.playerinterfacemod;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.dragon9815.dragoncore.registry.UpgradeRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.stegr.playerinterfacemod.handler.ConfigurationHandler;
import net.stegr.playerinterfacemod.handler.PlimEventHandler;
import net.stegr.playerinterfacemod.helpers.LogHelper;
import net.stegr.playerinterfacemod.init.ModBlocks;
import net.stegr.playerinterfacemod.init.ModItems;
import net.stegr.playerinterfacemod.init.ModTileEntities;
import net.stegr.playerinterfacemod.integration.ModRegistry;
import net.stegr.playerinterfacemod.network.PacketHandler;
import net.stegr.playerinterfacemod.proxy.CommonProxy;
import net.stegr.playerinterfacemod.recipe.RecipeRegistry;
import net.stegr.playerinterfacemod.reference.ModIDs;
import net.stegr.playerinterfacemod.reference.Reference;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = "required-after:DragonCore@[1.0,)", acceptableRemoteVersions = Reference.VERSION)
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

        PacketHandler.init();

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        UpgradeRegistry.instance().registerUpgrade(ModItems.ItemTransferUpgrade);
        //UpgradeRegistry.instance().registerUpgrade(ModItems.ItemBufferUpgrade);
        UpgradeRegistry.instance().registerUpgrade(ModItems.RFTransferUpgrade);
        UpgradeRegistry.instance().registerUpgrade(ModItems.ComperatorUpgrade);

        proxy.registerRenderers();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ModRegistry.init();

        RecipeRegistry.init();
        RecipeRegistry.loadRecipes(ConfigurationHandler.recipesType);

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
        MinecraftForge.EVENT_BUS.register(new PlimEventHandler());
        FMLCommonHandler.instance().bus().register(new PlimEventHandler());

        LogHelper.info(">>> Buildcraft Loaded: " + Loader.isModLoaded(ModIDs.BUILDCRAFT));
    }
}
