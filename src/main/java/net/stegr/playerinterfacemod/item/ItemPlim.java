package net.stegr.playerinterfacemod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.stegr.playerinterfacemod.creativetab.CreativeTabPlim;
import net.stegr.playerinterfacemod.reference.Reference;
import net.stegr.repackage.cofh.lib.util.helpers.StringHelper;

import java.util.List;

public class ItemPlim extends Item
{
    public ItemPlim()
    {
        super();

        this.setCreativeTab(CreativeTabPlim.PLIM_TAB);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        return String.format("item.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    public String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(this.getUnwrappedUnlocalizedName(this.getUnlocalizedName()));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltips, boolean par4)
    {
        super.addInformation(stack, player, tooltips, par4);

        if(StringHelper.isShiftKeyDown())
        {
            String[] description;

            description = ("" + StatCollector.translateToLocal(this.getUnlocalizedName() + ".description")).trim().split(";");

            for (String line : description)
            {
                tooltips.add(StringHelper.GREEN + line);
            }

            this.addExtraInfo(stack, player, tooltips, par4);
        }
        else
        {
            tooltips.add(("" + StringHelper.BRIGHT_BLUE + StatCollector.translateToLocal("string.moreInformation")).trim());
        }
    }

    protected void addExtraInfo(ItemStack stack, EntityPlayer player, List tooltips, boolean par4) {}
}
