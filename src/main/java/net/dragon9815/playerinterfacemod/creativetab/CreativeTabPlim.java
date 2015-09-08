package net.dragon9815.playerinterfacemod.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.dragon9815.playerinterfacemod.init.ModBlocks;
import net.dragon9815.playerinterfacemod.reference.Reference;

public class CreativeTabPlim {
    public static final CreativeTabs PLIM_TAB = new CreativeTabs(Reference.MOD_ID) {
        @Override

        public Item getTabIconItem() {
            return Item.getItemFromBlock(ModBlocks.player_interface);
        }
    };
}
