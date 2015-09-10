package net.dragon9815.playerinterfacemod.helpers;

import net.dragon9815.playerinterfacemod.inventory.InventoryTrash;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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
}
