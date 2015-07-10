package net.stegr.playerinterfacemod.item.upgrade;

import net.minecraft.util.StatCollector;
import net.stegr.playerinterfacemod.item.ItemPlim;

public abstract class ItemUpgrade extends ItemPlim implements IUpgrade
{
    public ItemUpgrade()
    {
        super();
    }

    @Override
    public String[] getPrerequisites()
    {
        return null;
    }

    @Override
    public String getLocalizedName()
    {
        return StatCollector.translateToLocal(this.getUnlocalizedName() + ".name");
    }
}
