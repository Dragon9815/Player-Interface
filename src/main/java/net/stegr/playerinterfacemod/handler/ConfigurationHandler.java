package net.stegr.playerinterfacemod.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import net.stegr.playerinterfacemod.reference.Reference;

import java.io.File;

public class ConfigurationHandler {
    public static Configuration configuration;
    public static Recipes recipesType;

    public static void init(File configFile) {
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration() {
        configuration.addCustomCategoryComment("recipes", "1 .. Vanilla\n2 .. Thermal Expansion (requires Thermal Expansion and Thermal Dynamics to be installed!)");
        recipesType = Recipes.values()[configuration.get("recipes", "recipeType", ((Loader.isModLoaded("ThermalExpansion") && Loader.isModLoaded("ThermalDynamics")) ? 2 : 1)).getInt() - 1];


        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
            // Konfiguration neu laden
            loadConfiguration();
        }
    }

    public enum Recipes {
        VANILLA("Vanilla"), THERMALEXPANSION("ThermalExpansion");

        private String name;

        Recipes(String name) {
            this.name = name;
        }
    }
}
