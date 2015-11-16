package net.dragon9815.playerinterfacemod.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTrash extends Container {

    public InventoryPlayer invPlayer;
    public InventoryTrash invTrash;

    public ContainerTrash(InventoryPlayer inventoryPlayer, InventoryTrash inventoryTrash) {
        this.invPlayer = inventoryPlayer;
        this.invTrash = inventoryTrash;

        int i;
        int j;

        // Trash Slots
        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventoryTrash, j + i * 9, 8 + j * 18, 8 + i * 18));
            }
        }

        // ArmorSlots
        for (i = 0; i < 4; ++i) {
            this.addSlotToContainer(new SlotArmor(this, inventoryPlayer, inventoryPlayer.getSizeInventory() - 1 - i, 8 + i * 18, 64, i));
        }

        // Main Inventory
        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + (i + 1) * 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // Hotbar
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer p_75134_1_) {
        this.invTrash.saveToNBT(p_75134_1_.getEntityData());
        super.onContainerClosed(p_75134_1_);
    }

    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotIndex < 31)
            {
                if (!this.mergeItemStack(itemstack1, 31, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 31, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
}
