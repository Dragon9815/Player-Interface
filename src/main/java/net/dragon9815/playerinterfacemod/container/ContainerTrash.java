package net.dragon9815.playerinterfacemod.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.dragon9815.playerinterfacemod.helpers.LogHelper;
import net.dragon9815.playerinterfacemod.inventory.InventoryTrash;
import net.dragon9815.playerinterfacemod.tileentity.TileEntityPlayerInterface;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.Iterator;

public class ContainerTrash extends Container {

    public InventoryPlayer invPlayer;
    public InventoryTrash invTrash;

    public ContainerTrash(InventoryPlayer inventoryPlayer, InventoryTrash inventoryTrash)
    {
        this.invPlayer = inventoryPlayer;
        this.invTrash = inventoryTrash;

        int i;
        int j;

        // Trash Slots
        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventoryTrash, j + i * 9, 7 + j * 18, 28 + i * 18));
            }
        }

        // ArmorSlots
        for (i = 0; i < 4; ++i)
        {
            this.addSlotToContainer(new SlotArmor(this, inventoryPlayer, inventoryPlayer.getSizeInventory() - 1 - i, 7 + i * 18, 253, i));
        }

        // Main Inventory
        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + (i + 1) * 9, 7 + j * 18, 83 + i * 18));
            }
        }

        // Hotbar
        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 7 + i * 18, 141));
        }
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer p_75134_1_)
    {
        super.onContainerClosed(p_75134_1_);
    }

    public boolean canInteractWith(EntityPlayer p_75145_1_)
    {
        return true;
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_)
    {
        return null;
    }
}
