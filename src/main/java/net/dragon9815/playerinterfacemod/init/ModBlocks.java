package net.dragon9815.playerinterfacemod.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.dragon9815.dragoncore.block.BlockUpgradeable;
import net.dragon9815.playerinterfacemod.block.BlockPlayerInterface;
import net.dragon9815.playerinterfacemod.item.itemblock.ItemBlockPlayerInterface;
import net.dragon9815.playerinterfacemod.reference.MachineNames;

public class ModBlocks {

    public static final BlockUpgradeable player_interface = new BlockPlayerInterface();

    public static void init() {
        //GameRegistry.registerBlock(player_interface, "pl_interface");
        GameRegistry.registerBlock(player_interface, ItemBlockPlayerInterface.class, MachineNames.PLAYER_INTERFACE);
    }
}
