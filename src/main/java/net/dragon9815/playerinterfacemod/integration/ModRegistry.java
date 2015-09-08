package net.dragon9815.playerinterfacemod.integration;

public class ModRegistry {
    public static final BaseModHelper Waila = new WailaModHelper();

    public static void init() {
        Waila.init();
    }
}
