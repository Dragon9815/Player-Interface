package net.stegr.playerinterfacemod.proxy;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.stegr.playerinterfacemod.client.gui.GuiSideConfig;

public abstract class CommonProxy implements IProxy, IGuiHandler
{
    public abstract EntityPlayer getClientPlayer();

    public void intermodComm(){}

    public void registerRenderers() {}

    public void registerTilEntitySpecialRenderers() {}

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if(ID == GuiSideConfig.GUI_ID)
            return new GuiSideConfig();

        return null;
    }
}
