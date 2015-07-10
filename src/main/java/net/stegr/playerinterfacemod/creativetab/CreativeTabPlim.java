package net.stegr.playerinterfacemod.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.stegr.playerinterfacemod.init.ModBlocks;
import net.stegr.playerinterfacemod.reference.Reference;

public class CreativeTabPlim
{
    public static final CreativeTabs PLIM_TAB = new CreativeTabs(Reference.MOD_ID)
    {
        @Override

        public Item getTabIconItem()
        {
            return Item.getItemFromBlock(ModBlocks.player_interface);
        }
    };
}
