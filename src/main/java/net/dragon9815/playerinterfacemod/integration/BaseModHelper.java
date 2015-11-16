package net.dragon9815.playerinterfacemod.integration;

import cpw.mods.fml.common.Loader;
import net.dragon9815.playerinterfacemod.helpers.LogHelper;

public abstract class BaseModHelper {
    public boolean loaded;

    public BaseModHelper() {
        this.loaded = false;
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    public void init() {
        if (Loader.isModLoaded(this.getModId())) {
            try {
                this.loaded = this.load();

                LogHelper.info("ModHelper loaded for " + this.getModId());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!this.loaded) {
            LogHelper.info("ModHelper not loaded for " + this.getModId());
        }
    }

    public boolean load() throws Exception {
        return false;
    }

    protected abstract String getModId();
}
