package net.stegr.plim.integration.waila.tile;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.stegr.plim.integration.waila.BaseWailaDataProvider;
import net.stegr.plim.item.upgrade.IUpgrade;
import net.stegr.plim.tileentity.TileEntityPlayerInterface;
import net.stegr.plim.utility.UpgradeRegistry;

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

        if( te instanceof TileEntityPlayerInterface)
        {
            final TileEntityPlayerInterface playerInterface = (TileEntityPlayerInterface)te;
            final EntityPlayer boundPlayer = playerInterface.getBoundPlayer();
            final Map<String, Integer> upgrades = playerInterface.installedUpgrades;
            final Set<String> upgradeNames = upgrades.keySet();

            currentToolTip.add("Bound Player: " + ((boundPlayer == null) ? "none" : boundPlayer.getDisplayName()));

            if(upgradeNames.size() > 0)
            {
                currentToolTip.add("Upgrades: ");
            }

            final Iterator<String> it1 = upgradeNames.iterator();
            while(it1.hasNext())
            {
                final String name = it1.next();
                final IUpgrade upgrade = UpgradeRegistry.getUpgrade(name);

                currentToolTip.add("- " + ((upgrade != null) ? upgrade.getLocalizedName() : name) + ": " + String.valueOf(upgrades.get(name)));
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