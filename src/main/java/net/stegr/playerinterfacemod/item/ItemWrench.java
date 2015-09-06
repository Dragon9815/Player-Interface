package net.stegr.playerinterfacemod.item;

import net.dragon9815.dragoncore.item.ItemBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.stegr.playerinterfacemod.PlayerInterfaceMod;
import net.stegr.playerinterfacemod.client.gui.GuiSideConfig;
import net.stegr.playerinterfacemod.creativetab.CreativeTabPlim;
import net.stegr.playerinterfacemod.reference.Reference;

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
        player.openGui(PlayerInterfaceMod.instance, GuiSideConfig.GUI_ID, world, x, y, z);

        return false;
    }
}
