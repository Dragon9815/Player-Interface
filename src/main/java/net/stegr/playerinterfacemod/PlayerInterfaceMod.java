package net.stegr.playerinterfacemod;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.stegr.playerinterfacemod.handler.ConfigurationHandler;
import net.stegr.playerinterfacemod.init.ModBlocks;
import net.stegr.playerinterfacemod.init.ModItems;
import net.stegr.playerinterfacemod.init.ModTileEntities;
import net.stegr.playerinterfacemod.integration.Waila;
import net.stegr.playerinterfacemod.network.DescriptionHandler;
import net.stegr.playerinterfacemod.proxy.CommonProxy;
import net.stegr.playerinterfacemod.recipe.RecipeRegistry;
import net.stegr.playerinterfacemod.reference.Reference;
import net.stegr.playerinterfacemod.utility.LogHelper;
import net.stegr.playerinterfacemod.utility.UpgradeRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME,version = Reference.VERSION, dependencies = "", acceptableRemoteVersions = Reference.VERSION)
public class PlayerInterfaceMod
{
    @Mod.Instance(Reference.MOD_ID)
    public static  PlayerInterfaceMod instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide =  Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModTileEntities.init();
        ModBlocks.init();
        ModItems.init();

        DescriptionHandler.init();

        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        UpgradeRegistry.init();
        UpgradeRegistry.registerUpgrade(ModItems.BufferUpgrade);
        UpgradeRegistry.registerUpgrade(ModItems.TransferUpgrade);
        UpgradeRegistry.registerUpgrade(ModItems.ComperatorUpgrade);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.intermodComm();

        RecipeRegistry.init();
        RecipeRegistry.loadRecipes(ConfigurationHandler.recipesType);
    }
}
