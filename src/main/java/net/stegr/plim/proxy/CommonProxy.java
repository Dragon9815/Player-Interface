package net.stegr.plim.proxy;

import net.minecraft.entity.player.EntityPlayer;

import javax.swing.text.html.parser.Entity;

public abstract class CommonProxy implements IProxy
{
    public abstract EntityPlayer getClientPlayer();
}
