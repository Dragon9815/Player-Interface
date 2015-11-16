package net.dragon9815.playerinterfacemod.item;

import net.dragon9815.dragoncore.item.ItemBase;
import net.dragon9815.playerinterfacemod.reference.Reference;

/*
 * This Item does nothing, it is just the logo for the TCon-Tab
 */
public class ItemTrashBin extends ItemBase {

    public ItemTrashBin() {
        super();

        this.setTextureName("trashBin");
        this.setUnlocalizedName("trashBin");
    }

    @Override
    public String getModID() {
        return Reference.MOD_ID;
    }
}
