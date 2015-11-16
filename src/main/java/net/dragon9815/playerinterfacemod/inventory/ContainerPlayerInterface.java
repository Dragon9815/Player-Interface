package net.dragon9815.playerinterfacemod.inventory;

import net.dragon9815.playerinterfacemod.tileentity.TileEntityPlayerInterface;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerPlayerInterface extends Container {
    private TileEntityPlayerInterface tilePlayerInterface;


    public ContainerPlayerInterface(InventoryPlayer playerInventory, TileEntityPlayerInterface tilePlayerInterface) {
        this.tilePlayerInterface = tilePlayerInterface;

        int i;

        for (i = 0; i < 5; i++) {
            this.addSlotToContainer(new Slot(tilePlayerInterface.getUpgradeInventory(), i, 37 + i * 21, 107));
        }

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tilePlayerInterface.isUseableByPlayer(player);
    }
}
