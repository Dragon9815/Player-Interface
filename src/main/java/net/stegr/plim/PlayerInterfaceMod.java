package net.stegr.plim;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.stegr.plim.init.ModBlocks;
import net.stegr.plim.init.ModItems;
import net.stegr.plim.init.ModTileEntities;
import net.stegr.plim.integration.Waila;
import net.stegr.plim.network.DescriptionHandler;
import net.stegr.plim.proxy.CommonProxy;
import net.stegr.plim.proxy.IProxy;
import net.stegr.plim.reference.Reference;
import net.stegr.plim.utility.LogHelper;
import net.stegr.plim.utility.UpgradeRegistry;

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
        new UpgradeRegistry();

        UpgradeRegistry.registerUpgrade(ModItems.BufferUpgrade);
        UpgradeRegistry.registerUpgrade(ModItems.TransferUpgrade);

        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.player_interface), "GDG", "DID", "GDG", 'G', Items.gold_ingot, 'D', Items.diamond, 'I', Blocks.iron_block);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.BufferUpgrade), "SIS", "GCG", "SIS", 'S', Blocks.stone, 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'C', Blocks.chest);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.TransferUpgrade), "SIS", "GHG", "SIS", 'S', Blocks.stone, 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'H', Blocks.hopper);


    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // TODO: Fix WAILA implementation
        Waila waila = new Waila();

        try
        {
            waila.init();
            LogHelper.info("Waila integration loaded");
        } catch (Throwable throwable)
        {
            throwable.printStackTrace();
            LogHelper.info("Waila integration not loaded");
        }
    }
}
