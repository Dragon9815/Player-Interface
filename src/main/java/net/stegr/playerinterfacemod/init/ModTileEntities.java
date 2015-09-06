package net.stegr.playerinterfacemod.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.stegr.playerinterfacemod.tileentity.TileEntityPlayerInterface;

public class ModTileEntities {
    public static void init() {
        GameRegistry.registerTileEntity(TileEntityPlayerInterface.class, "te_pl_interface");
    }
}
