package net.stegr.playerinterfacemod.integration.waila.tile;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.dragon9815.dragoncore.helpers.StringHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.stegr.playerinterfacemod.integration.waila.BaseWailaDataProvider;
import net.stegr.playerinterfacemod.tileentity.TileEntityPlayerInterface;

import java.util.List;

public class PlayerInterfaceWailaDataProvider extends BaseWailaDataProvider {
    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currentToolTip, IWailaDataAccessor accessor, IWailaConfigHandler iWailaConfigHandler) {
        final TileEntity te = accessor.getTileEntity();

        if (te instanceof TileEntityPlayerInterface) {
            final TileEntityPlayerInterface playerInterface = (TileEntityPlayerInterface) te;
            final String playerName = playerInterface.playerName;

            currentToolTip.add(StringHelper.localize("waila.owner") + " " + ((playerName == null) ? "Error" : playerName));
        }

        return currentToolTip;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP entityPlayerMP, TileEntity tileEntity, NBTTagCompound tag, World world, int i, int i1, int i2) {
        return tag;
    }
}