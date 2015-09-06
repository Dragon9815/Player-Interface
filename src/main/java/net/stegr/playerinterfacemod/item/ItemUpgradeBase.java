package net.stegr.playerinterfacemod.item;

import net.dragon9815.dragoncore.item.ItemBase;
import net.stegr.playerinterfacemod.creativetab.CreativeTabPlim;
import net.stegr.playerinterfacemod.reference.Reference;

public class ItemUpgradeBase extends ItemBase {
    public ItemUpgradeBase() {
        super();

        this.setCreativeTab(CreativeTabPlim.PLIM_TAB);
        this.setUnlocalizedName("upgrade_base");
        this.setTextureName("upgrade_base");
    }

    @Override
    public String getModID() {
        return Reference.MOD_ID;
    }
}
