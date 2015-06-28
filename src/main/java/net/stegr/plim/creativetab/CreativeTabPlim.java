package net.stegr.plim.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.stegr.plim.reference.Reference;

public class CreativeTabPlim
{
    public static final CreativeTabs PLIM_TAB = new CreativeTabs(Reference.MOD_ID)
    {
        @Override
        public Item getTabIconItem()
        {
            return Items.rotten_flesh;
        }
    };
}
