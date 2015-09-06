package net.stegr.playerinterfacemod.recipe;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.MissingModsException;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.DefaultArtifactVersion;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.stegr.playerinterfacemod.helpers.LogHelper;
import net.stegr.playerinterfacemod.init.ModBlocks;
import net.stegr.playerinterfacemod.init.ModItems;

import java.util.Collections;

public class RecipeSetThermalExpansion extends RecipeSet {
    private static final String TE = "ThermalExpansion";
    private static final String TD = "ThermalDynamics";

    private static ItemStack tesseract;
    private static ItemStack warpItemduct;
    private static ItemStack warpItemductOpaque;
    private static ItemStack resonantFluxduct;


    public void init() {
        boolean TELoaded = Loader.isModLoaded(TE);
        boolean TDLoaded = Loader.isModLoaded(TD);

        if (!TELoaded || !TDLoaded) {
            LogHelper.fatal("Thermal Expansion and Thermal Dynamics is required for Thermal Expansion recipes.");
            throw new MissingModsException(Collections.singleton((ArtifactVersion) new DefaultArtifactVersion((!TELoaded) ? TE : TD)));
        }

        getItems();
        setRecipes();
    }

    public void getItems() {
        /* Blocks */
        tesseract = new ItemStack(GameRegistry.findBlock(TE, "Tesseract"), 1, 0);
        warpItemduct = new ItemStack(GameRegistry.findBlock(TD, "ThermalDynamics_32"), 1, 4);
        warpItemductOpaque = new ItemStack(GameRegistry.findBlock(TD, "ThermalDynamics_32"), 1, 5);
        resonantFluxduct = new ItemStack(GameRegistry.findBlock(TD, "ThermalDynamics_0"), 1, 4);

		/* Items */
    }

    public void setRecipes() {
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.player_interface), "BGB", "GDG", "BGB", 'B', ModItems.UpgradeBase, 'G', Items.gold_ingot, 'D', tesseract);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.UpgradeBase), "SIS", "GDG", "SIS", 'D', Blocks.obsidian, 'S', Blocks.stone, 'I', Items.iron_ingot, 'G', Items.gold_ingot);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.ItemTransferUpgrade), "GAI", "ABA", "IAG", 'B', ModItems.UpgradeBase, 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', warpItemduct);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.ItemTransferUpgrade), "IAG", "ABA", "GAI", 'B', ModItems.UpgradeBase, 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', warpItemduct);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.ItemTransferUpgrade), "GAI", "ABA", "IAG", 'B', ModItems.UpgradeBase, 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', warpItemductOpaque);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.ItemTransferUpgrade), "IAG", "ABA", "GAI", 'B', ModItems.UpgradeBase, 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', warpItemductOpaque);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.RFTransferUpgrade), "IAG", "ABA", "GAI", 'B', ModItems.UpgradeBase, 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', resonantFluxduct);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.ComperatorUpgrade), "GAI", "ABA", "IAG", 'B', ModItems.UpgradeBase, 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', Items.comparator);
        LogHelper.info("Recipes loaded");
    }
}
