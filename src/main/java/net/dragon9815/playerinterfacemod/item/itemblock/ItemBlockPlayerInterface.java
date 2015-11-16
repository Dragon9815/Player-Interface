package net.dragon9815.playerinterfacemod.item.itemblock;

import net.dragon9815.dragoncore.item.itemblock.ItemBlockUpgradeable;
import net.dragon9815.playerinterfacemod.creativetab.CreativeTabPlim;
import net.dragon9815.playerinterfacemod.reference.MachineNames;
import net.minecraft.block.Block;

public class ItemBlockPlayerInterface extends ItemBlockUpgradeable {
    public ItemBlockPlayerInterface(Block block) {
        super(block);

        this.setCreativeTab(CreativeTabPlim.PLIM_TAB);
        this.setUnlocalizedName("pl_interface");
    }

    @Override
    protected String getMachineName() {
        return MachineNames.PLAYER_INTERFACE;
    }
}
