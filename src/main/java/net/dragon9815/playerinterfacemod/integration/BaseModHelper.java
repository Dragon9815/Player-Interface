package net.dragon9815.playerinterfacemod.integration;

import cpw.mods.fml.common.Loader;
import net.dragon9815.playerinterfacemod.helpers.LogHelper;

public abstract class BaseModHelper {
    protected boolean loaded;

    public BaseModHelper() {
        this.loaded = false;
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    public void init() {
        if (Loader.isModLoaded(this.getModId())) {
            try {
                this.load();

                LogHelper.info("ModHelper loaded for " + this.getModId());
                this.loaded = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!this.loaded) {
            LogHelper.info("ModHelper not loaded for " + this.getModId());
        }
    }

    public void load() throws Exception {

    }

    protected abstract String getModId();
}
