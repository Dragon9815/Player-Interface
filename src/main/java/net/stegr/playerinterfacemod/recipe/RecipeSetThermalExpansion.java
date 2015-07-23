package net.stegr.playerinterfacemod.recipe;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.MissingModsException;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.DefaultArtifactVersion;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.stegr.playerinterfacemod.init.ModBlocks;
import net.stegr.playerinterfacemod.init.ModItems;
import net.stegr.playerinterfacemod.utility.LogHelper;

import java.util.Collections;

public class RecipeSetThermalExpansion extends RecipeSet
{
    private static final String TE = "ThermalExpansion";
    private static final String TF = "ThermalFoundation";
    private static final String TD = "ThermalDynamics";

    private static ItemStack tesseract;
    private static ItemStack warpItemduct;
    private static ItemStack warpItemductOpaque;
    private static ItemStack cacheResonant;


    public void init()
    {
        boolean TELoaded = Loader.isModLoaded(TE);
        boolean TDLoaded = Loader.isModLoaded(TD);

        if(!TELoaded || !TDLoaded)
        {
            LogHelper.fatal("Thermal Expansion and Thermal Dynamics is required for Thermal Expansion recipes.");
            throw new MissingModsException(Collections.singleton((ArtifactVersion) new DefaultArtifactVersion((!TELoaded) ? TE : TD)));
        }

        getItems();
        setRecipes();
    }

    public void getItems()
    {
        /* Blocks */
        tesseract = new ItemStack(GameRegistry.findBlock(TE, "Tesseract"), 1, 0);
        warpItemduct = new ItemStack(GameRegistry.findBlock(TD, "ThermalDynamics_32"), 1, 4);
        warpItemductOpaque = new ItemStack(GameRegistry.findBlock(TD, "ThermalDynamics_32"), 1, 5);
        cacheResonant = new ItemStack(GameRegistry.findBlock(TE, "Cache"), 1, 4);

		/* Items */

        LogHelper.info("Items loaded: " + String.valueOf(tesseract));
    }

    public void setRecipes()
    {
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.player_interface), "GDG", "DTD", "GDG", 'G', Blocks.gold_block, 'D', Blocks.diamond_block, 'T', tesseract);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.BufferUpgrade), "SIS", "GCG", "SIS", 'S', Blocks.obsidian, 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'C', cacheResonant);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.TransferUpgrade), "SIS", "GHG", "SIS", 'S', Blocks.obsidian, 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'H', warpItemduct);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.TransferUpgrade), "SIS", "GHG", "SIS", 'S', Blocks.obsidian, 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'H', warpItemductOpaque);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.ComperatorUpgrade), "SIS", "GCG", "SIS", 'S', Blocks.obsidian, 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'C', Items.comparator);
        LogHelper.info("Recipes loaded");
    }
}
