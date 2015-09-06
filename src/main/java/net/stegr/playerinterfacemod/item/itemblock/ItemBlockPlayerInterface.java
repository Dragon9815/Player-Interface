package net.stegr.playerinterfacemod.item.itemblock;

import net.dragon9815.dragoncore.item.itemblock.ItemBlockUpgradeable;
import net.minecraft.block.Block;
import net.stegr.playerinterfacemod.creativetab.CreativeTabPlim;
import net.stegr.playerinterfacemod.reference.MachineNames;

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
