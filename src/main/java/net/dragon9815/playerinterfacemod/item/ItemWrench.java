package net.dragon9815.playerinterfacemod.item;

import net.dragon9815.dragoncore.item.ItemBase;
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
        player.openGui(PlayerInterfaceMod.instance, GuiSideConfig.GUI_ID, world, x, y, z);

        return false;
    }
}
