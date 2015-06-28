package net.stegr.plim.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.stegr.plim.block.BlockPlayerInterface;
import net.stegr.plim.block.BlockPlim;
import net.stegr.plim.block.BlockPlimTileEntity;

public class ModBlocks
{

    public static final BlockPlimTileEntity player_interface = new BlockPlayerInterface();

    public static void init()
    {
        GameRegistry.registerBlock(player_interface, "pl_interface");
    }
}
