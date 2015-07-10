package net.stegr.playerinterfacemod.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.stegr.playerinterfacemod.block.BlockPlayerInterface;
import net.stegr.playerinterfacemod.block.BlockPlimTileEntity;

public class ModBlocks
{

    public static final BlockPlimTileEntity player_interface = new BlockPlayerInterface();

    public static void init()
    {
        GameRegistry.registerBlock(player_interface, "pl_interface");
    }
}
