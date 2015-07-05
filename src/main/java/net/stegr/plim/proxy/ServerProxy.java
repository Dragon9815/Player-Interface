package net.stegr.plim.proxy;

import net.minecraft.entity.player.EntityPlayer;

public class ServerProxy extends CommonProxy
{

    @Override
    public EntityPlayer getClientPlayer()
    {
        return null;
    }
}
