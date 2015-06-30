package net.stegr.plim.item.upgrade;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.stegr.plim.item.ItemPlim;
import net.stegr.plim.reference.Reference;
import net.stegr.plim.tileentity.TileEntityPlayerInterface;
import net.stegr.plim.tileentity.TileEntityUpgradeable;

public abstract class ItemUpgrade extends ItemPlim implements IUpgrade
{
    public ItemUpgrade()
    {
        super();
    }
}
