package net.stegr.playerinterfacemod.integration.waila.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.stegr.playerinterfacemod.inventory.InventoryUpgradeable;
import net.stegr.playerinterfacemod.item.upgrade.ItemUpgrade;
import net.stegr.playerinterfacemod.tileentity.TileEntityPlayerInterface;
import net.stegr.repackage.cofh.lib.util.helpers.StringHelper;
import net.stegr.repackage.mcp.mobius.waila.api.IWailaConfigHandler;
import net.stegr.repackage.mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.stegr.playerinterfacemod.integration.waila.BaseWailaDataProvider;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PlayerInterfaceWailaDataProvider extends BaseWailaDataProvider
{
    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currentToolTip, IWailaDataAccessor accessor, IWailaConfigHandler iWailaConfigHandler)
    {
        final TileEntity te = accessor.getTileEntity();

        if(te instanceof TileEntityPlayerInterface)
        {
            final TileEntityPlayerInterface playerInterface = (TileEntityPlayerInterface)te;
            final EntityPlayer boundPlayer = playerInterface.getBoundPlayer();
            final ItemStack[] upgrades = ((InventoryUpgradeable)playerInterface.getUpgradeInventory()).getInstalledUpgrades();

            currentToolTip.add(StringHelper.localize("waila.boundPlayer") + " " + ((boundPlayer == null) ? "none" : boundPlayer.getDisplayName()));

            if(upgrades.length > 0)
            {
                currentToolTip.add(StringHelper.localize("waila.upgrades"));
            }

            for(ItemStack upgrade : upgrades)
            {
                if(upgrade != null)
                {
                    currentToolTip.add("- " + (((ItemUpgrade)upgrade.getItem()).getLocalizedName()) + ": " + String.valueOf(upgrade.stackSize));
                }
            }
        }

        return currentToolTip;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP entityPlayerMP, TileEntity tileEntity, NBTTagCompound tag, World world, int i, int i1, int i2)
    {
        return tag;
    }
}