package net.stegr.playerinterfacemod.item;

import cofh.api.item.IToolHammer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.stegr.playerinterfacemod.PlayerInterfaceMod;
import net.stegr.playerinterfacemod.client.gui.GuiSideConfig;

public class ItemWrench extends ItemPlim//implements IToolHammer
{
    public ItemWrench()
    {
        super();

        this.setUnlocalizedName("wrench");
        this.setTextureName("wrench");
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        player.openGui(PlayerInterfaceMod.instance, GuiSideConfig.GUI_ID, world, x, y, z);

        return false;
    }
}
