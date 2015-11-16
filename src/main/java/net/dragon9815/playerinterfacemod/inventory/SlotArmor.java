package net.dragon9815.playerinterfacemod.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class SlotArmor extends Slot {

    public final int armorType;
    public final Container parent;

    public SlotArmor(Container container, IInventory inventory, int par3, int par4, int par5, int armorType) {
        super(inventory, par3, par4, par5);
        this.parent = container;
        this.armorType = armorType;
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        Item item = (itemStack == null) ? null : itemStack.getItem();

        boolean isValidArmor = false;
        if (item instanceof ItemArmor) {
            isValidArmor = ((ItemArmor) item).armorType == armorType;
        }

        return isValidArmor;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getBackgroundIconIndex() {
        return ItemArmor.func_94602_b(this.armorType);
    }
}
