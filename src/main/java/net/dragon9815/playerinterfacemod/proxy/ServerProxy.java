package net.dragon9815.playerinterfacemod.proxy;

import net.minecraft.entity.player.EntityPlayer;

public class ServerProxy extends CommonProxy {

    @Override
    public EntityPlayer getClientPlayer() {
        return null;
    }

    @Override
    public void registerClientStuff() {

    }
}
