package net.stegr.plim.creativetab;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.stegr.plim.init.ModBlocks;
import net.stegr.plim.reference.Reference;

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
