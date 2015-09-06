package net.stegr.playerinterfacemod.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.stegr.playerinterfacemod.init.ModBlocks;
import net.stegr.playerinterfacemod.init.ModItems;

public class RecipeSetVanilla extends RecipeSet {
    @Override
    protected void setRecipes() {
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.player_interface), "BGB", "GDG", "BGB", 'B', ModItems.UpgradeBase, 'G', Items.gold_ingot, 'D', Blocks.diamond_block);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.UpgradeBase), "SIS", "GDG", "SIS", 'D', Blocks.obsidian, 'S', Blocks.stone, 'I', Items.iron_ingot, 'G', Items.gold_ingot);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.ItemTransferUpgrade), "GAI", "ABA", "IAG", 'B', ModItems.UpgradeBase, 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', Blocks.hopper);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.ComperatorUpgrade), "GAI", "ABA", "IAG", 'B', ModItems.UpgradeBase, 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', Items.comparator);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.RFTransferUpgrade), "IAG", "ABA", "GAI", 'B', ModItems.UpgradeBase, 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'A', Items.repeater);
    }

    @Override
    protected void getItems() {

    }

    @Override
    public void init() {
        getItems();
        setRecipes();
    }
}
