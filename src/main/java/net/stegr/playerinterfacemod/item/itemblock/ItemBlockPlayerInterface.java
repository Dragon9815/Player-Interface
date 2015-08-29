package net.stegr.playerinterfacemod.item.itemblock;

import net.minecraft.block.Block;
import net.stegr.playerinterfacemod.reference.MachineNames;

public class ItemBlockPlayerInterface extends ItemBlockUpgradeable
{
    public ItemBlockPlayerInterface(Block block)
    {
        super(block);

        this.setUnlocalizedName("pl_interface");
    }

    @Override
    protected String getMachineName()
    {
        return MachineNames.PLAYER_INTERFACE;
    }
}
