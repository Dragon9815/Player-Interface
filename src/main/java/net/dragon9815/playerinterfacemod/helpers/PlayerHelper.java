package net.dragon9815.playerinterfacemod.helpers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import java.util.List;
import java.util.UUID;

public class PlayerHelper {
    public static EntityPlayer getPlayer(UUID playerUUID) {
        for (EntityPlayer player : (List<EntityPlayer>) MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
            if (player.getUniqueID() != null && player.getUniqueID().equals(playerUUID)) {
                LogHelper.info("Found Player");
                return player;
            }
        }

        return null;
    }
}
