package net.dragon9815.playerinterfacemod.item;

import net.dragon9815.dragoncore.item.ItemBase;
import net.dragon9815.playerinterfacemod.tileentity.TileEntityPlayerInterface;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.dragon9815.playerinterfacemod.PlayerInterfaceMod;
import net.dragon9815.playerinterfacemod.client.gui.GuiSideConfig;
import net.dragon9815.playerinterfacemod.creativetab.CreativeTabPlim;
import net.dragon9815.playerinterfacemod.reference.Reference;

public class ItemWrench extends ItemBase {
    public ItemWrench() {
        super();

        this.setCreativeTab(CreativeTabPlim.PLIM_TAB);
        this.setUnlocalizedName("wrench");
        this.setTextureName("wrench");
    }

    @Override
    public String getModID() {
        return Reference.MOD_ID;
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        return false;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float f1, float f2, float f3) {

        return true;
    }
}
