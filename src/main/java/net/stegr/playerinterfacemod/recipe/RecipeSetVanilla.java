package net.stegr.playerinterfacemod.recipe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.stegr.playerinterfacemod.init.ModBlocks;
import net.stegr.playerinterfacemod.init.ModItems;

public class RecipeSetVanilla extends RecipeSet
{
    @Override
    protected void setRecipes()
    {
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.player_interface), "GDG", "DID", "GDG", 'G', Blocks.gold_block, 'D', Blocks.diamond_block, 'I', Blocks.iron_block);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.BufferUpgrade), "SIS", "GCG", "SIS", 'S', Blocks.obsidian, 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'C', Blocks.chest);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.TransferUpgrade), "SIS", "GHG", "SIS", 'S', Blocks.obsidian, 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'H', Blocks.hopper);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.ComperatorUpgrade), "SIS", "GCG", "SIS", 'S', Blocks.obsidian, 'I', Items.iron_ingot, 'G', Items.gold_ingot, 'C', Items.comparator);
    }

    @Override
    protected void getItems()
    {

    }

    @Override
    public void init()
    {
        getItems();
        setRecipes();
    }
}
