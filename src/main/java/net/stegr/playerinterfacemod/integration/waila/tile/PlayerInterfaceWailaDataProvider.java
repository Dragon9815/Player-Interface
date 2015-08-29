package net.stegr.playerinterfacemod.integration.waila.tile;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.stegr.playerinterfacemod.integration.waila.BaseWailaDataProvider;
import net.stegr.playerinterfacemod.inventory.InventoryUpgradeable;
import net.stegr.playerinterfacemod.item.ItemUpgrade;
import net.stegr.playerinterfacemod.registry.UpgradeRegistry;
import net.stegr.playerinterfacemod.tileentity.TileEntityPlayerInterface;
import net.stegr.repackage.cofh.lib.util.helpers.StringHelper;

import java.util.List;

public class PlayerInterfaceWailaDataProvider extends BaseWailaDataProvider
{
    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currentToolTip, IWailaDataAccessor accessor, IWailaConfigHandler iWailaConfigHandler)
    {
        final TileEntity te = accessor.getTileEntity();

        if(te instanceof TileEntityPlayerInterface)
        {
            final TileEntityPlayerInterface playerInterface = (TileEntityPlayerInterface)te;
            final String playerName = playerInterface.playerName;
            final ItemStack[] upgrades = playerInterface.getUpgradeInventory().getInstalledUpgrades();

            currentToolTip.add(StringHelper.localize("waila.owner") + " " + ((playerName == null) ? "Error" : playerName));

            boolean wrote = false;

            for(ItemStack upgrade : upgrades)
            {
                if(upgrade != null)
                {
                    if(!wrote)
                    {
                        currentToolTip.add(StringHelper.localize("waila.upgrades"));
                        wrote = true;
                    }
                    currentToolTip.add(" - " + (((ItemUpgrade)upgrade.getItem()).getLocalizedName()) + ((UpgradeRegistry.instance().getNumUpgradesValidForMachine(((ItemUpgrade)upgrade.getItem()).getUpgradeID(), "pl_interface") > 1) ? (": " + String.valueOf(upgrade.stackSize)) : ""));
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