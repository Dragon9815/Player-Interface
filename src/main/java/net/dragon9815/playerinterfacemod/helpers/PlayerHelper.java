package net.dragon9815.playerinterfacemod.helpers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerHelper {
    public static EntityPlayer getPlayer(UUID playerUUID) {
        for (EntityPlayer player : (List<EntityPlayer>) MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
            if (player.getUniqueID().equals(playerUUID)) {
                return player;
            }
        }

        return null;
    }

    public static int canInsertIntoInventory(EntityPlayer player, ItemStack itemStack) {
        if (player == null || itemStack == null) {
            return 0;
        }

        int numInserted = 0;

        while (itemStack.stackSize > numInserted) {
            int slotNum = getSlotWithItem(player.inventoryContainer, itemStack, true);

            if (slotNum == -1) {
                slotNum = getNextFreeSlot(player.inventoryContainer);


                if (slotNum == -1) {
                    return 0;
                }
            } else {
                if (player.inventoryContainer.getSlot(slotNum).getStack().isStackable()) {

                }
            }
            //numInserted = Math.min(itemStack1.stackSize, itemStack2.getMaxStackSize() - itemStack2.stackSize); // max Stack-size - current Stack-size = Num items can be inserted
        }

        return numInserted;
    }

    public static int getSlotWithItem(Container playerContainer, ItemStack itemStack, final boolean canInsert) {
        if (playerContainer == null || itemStack == null) {
            return -1;
        }

        for (Slot slot : (ArrayList<Slot>) playerContainer.inventorySlots) {
            if (slot == null) // Null-check
            {
                continue;
            }

            if (slot.getHasStack()) {
                if (slot.getStack().isItemEqual(itemStack)) {
                    if (!canInsert) {
                        return slot.getSlotIndex();
                    } else {
                        if (slot.getStack().stackSize < slot.getStack().getMaxStackSize()) {
                            return slot.getSlotIndex();
                        }
                    }
                }
            }
        }

        return -1;
    }

    public static int getNextFreeSlot(final Container playerContainer) {
        if (playerContainer == null) {
            return -1;
        }

        for (Slot slot : (ArrayList<Slot>) playerContainer.inventorySlots) {
            if (slot == null) // Null-check
            {
                continue;
            }

            if (!slot.getHasStack()) {
                return slot.getSlotIndex();
            }
        }

        return -1;
    }
}
