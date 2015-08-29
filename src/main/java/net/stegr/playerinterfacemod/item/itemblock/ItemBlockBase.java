package net.stegr.playerinterfacemod.item.itemblock;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.stegr.playerinterfacemod.creativetab.CreativeTabPlim;
import net.stegr.repackage.cofh.lib.util.helpers.StringHelper;

import java.util.List;

public abstract class ItemBlockBase extends ItemBlock
{
    public ItemBlockBase(Block block)
    {
        super(block);

        this.setCreativeTab(CreativeTabPlim.PLIM_TAB);
    }

    public String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
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
