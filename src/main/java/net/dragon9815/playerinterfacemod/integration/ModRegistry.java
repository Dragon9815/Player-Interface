package net.dragon9815.playerinterfacemod.integration;

public class ModRegistry {
    public static final WailaModHelper Waila = new WailaModHelper();
    public static final TConstructModHelper TConstruct = new TConstructModHelper();

    public static void init() {
        Waila.init();
        TConstruct.init();
    }
}
