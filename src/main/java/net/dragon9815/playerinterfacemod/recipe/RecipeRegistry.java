package net.dragon9815.playerinterfacemod.recipe;

import net.dragon9815.playerinterfacemod.handler.ConfigurationHandler;

import java.util.HashMap;
import java.util.Map;

public class RecipeRegistry {
    private final static RecipeSet VanillaRecipes = new RecipeSetVanilla();
    private final static RecipeSet ThermalExpansionRecipes = new RecipeSetThermalExpansion();
    private static Map<ConfigurationHandler.Recipes, RecipeSet> recipes;

    public static void init() {
        recipes = new HashMap<ConfigurationHandler.Recipes, RecipeSet>();

        addRecipeSet(VanillaRecipes, ConfigurationHandler.Recipes.VANILLA);
        addRecipeSet(ThermalExpansionRecipes, ConfigurationHandler.Recipes.THERMALEXPANSION);
    }

    private static void addRecipeSet(RecipeSet set, ConfigurationHandler.Recipes recipeType) {
        recipes.put(recipeType, set);
    }

    public static void loadRecipes(ConfigurationHandler.Recipes recipeType) {
        RecipeSet set = recipes.get(recipeType);

        set.init();
    }
}
