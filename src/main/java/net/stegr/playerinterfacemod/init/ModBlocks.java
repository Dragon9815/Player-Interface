package net.stegr.playerinterfacemod.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.stegr.playerinterfacemod.block.BlockPlayerInterface;
import net.stegr.playerinterfacemod.block.BlockTileEntityUpgradeable;
import net.stegr.playerinterfacemod.item.itemblock.ItemBlockPlayerInterface;
import net.stegr.playerinterfacemod.reference.MachineNames;

public class ModBlocks
{

    public static final BlockTileEntityUpgradeable player_interface = new BlockPlayerInterface();

    public static void init()
    {
        //GameRegistry.registerBlock(player_interface, "pl_interface");
        GameRegistry.registerBlock(player_interface, ItemBlockPlayerInterface.class, MachineNames.PLAYER_INTERFACE);
    }
}
