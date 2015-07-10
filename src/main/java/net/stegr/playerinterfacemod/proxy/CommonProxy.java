package net.stegr.playerinterfacemod.proxy;

import net.minecraft.entity.player.EntityPlayer;

public abstract class CommonProxy implements IProxy
{
    public abstract EntityPlayer getClientPlayer();
}
