package net.stegr.playerinterfacemod.item.upgrade;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.stegr.playerinterfacemod.item.ItemPlim;
import net.stegr.playerinterfacemod.utility.UpgradeRegistry;

import java.util.List;

public abstract class ItemUpgrade extends ItemPlim
{
    public ItemUpgrade()
    {
        super();
    }

    public String[] getPrerequisites()
    {
        return null;
    }

    public String getLocalizedName()
    {
        return StatCollector.translateToLocal(this.getUnlocalizedName() + ".name");
    }

    public abstract String getUpgradeID();

    @Override
    public void addExtraInfo(ItemStack stack, EntityPlayer player, List tooltips, boolean par4)
    {
        String[] reqs = this.getPrerequisites();

        if(reqs != null && reqs.length > 0)
        {
            tooltips.add("");
            tooltips.add(("" + StatCollector.translateToLocal("string.requirements")).trim() + ":");

            for (String req : reqs)
            {
                tooltips.add(" - " + UpgradeRegistry.getUpgrade(req).getLocalizedName());
            }
        }
    }
}
