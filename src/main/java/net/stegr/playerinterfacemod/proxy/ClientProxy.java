package net.stegr.playerinterfacemod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.stegr.playerinterfacemod.integration.Waila;
import net.stegr.playerinterfacemod.utility.LogHelper;

public class ClientProxy extends CommonProxy
{
    @Override
    public EntityPlayer getClientPlayer()
    {
        return Minecraft.getMinecraft().thePlayer;
    }

    @Override
    public void intermodComm(){
        Waila waila = new Waila();

        try
        {
            waila.init();
            LogHelper.info("Waila integration loaded");
        } catch (Throwable throwable)
        {
            throwable.printStackTrace();
            LogHelper.info("Waila integration not loaded");
        }
    }
}
