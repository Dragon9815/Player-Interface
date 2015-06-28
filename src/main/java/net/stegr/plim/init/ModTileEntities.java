package net.stegr.plim.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.stegr.plim.reference.Reference;
import net.stegr.plim.tileentity.TileEntityPlayerInterface;

public class ModTileEntities
{
    public static void init()
    {
        GameRegistry.registerTileEntity(TileEntityPlayerInterface.class, "te_pl_interface");
    }
}
