package net.stegr.plim.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentStyle;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.stegr.plim.item.upgrade.IUpgrade;
import net.stegr.plim.item.upgrade.ItemUpgrade;
import net.stegr.plim.tileentity.TileEntityPlayerInterface;
import net.stegr.plim.tileentity.TileEntityUpgradeable;
import net.stegr.plim.utility.LogHelper;


public class BlockPlayerInterface extends BlockPlimTileEntity implements IBlockUpgradeable
{
    public BlockPlayerInterface()
    {
        super(Material.iron);
        this.setBlockName("pl_interface");
        this.setBlockTextureName("pl_interface");

        //this.setHarvestLevel("iron", 2);

        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setStepSound(soundTypeMetal);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float par7, float par8, float par9)
    {
        TileEntityPlayerInterface te = (TileEntityPlayerInterface)world.getTileEntity(x, y, z);

        if(player.getHeldItem() != null)
        {
            if (player.getHeldItem().getItem() instanceof ItemUpgrade)
            {
                ItemUpgrade item = (ItemUpgrade) player.getHeldItem().getItem();

                if (doUpgrade(item, te))
                {
                    player.getHeldItem().stackSize--;
                    return true;
                }
            }
        }

        if(!(player instanceof FakePlayer) && !world.isRemote)
        {
            if(player.isSneaking())
            {
                if(te.boundPlayer != null)
                {
                    te.boundPlayer = null;
                    te.markDirty();
                    player.addChatMessage(new ChatComponentText("Player unbound"));
                    LogHelper.info("Player unbound");
                }
            }
            else
            {
                if(te.boundPlayer == null)
                {
                    te.boundPlayer = player;
                    te.markDirty();
                    player.addChatMessage(new ChatComponentText("Player bound"));
                    LogHelper.info("Player bound: " + player.getUniqueID().toString());
                }
            }
        }

        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityPlayerInterface();
    }

    @Override
    public int getUpgradeSlots()
    {
        return 10;
    }

    @Override
    public boolean doUpgrade(IUpgrade upgrade, TileEntityUpgradeable tileEntity)
    {
        if(tileEntity.installedUpgrades.size() < this.getUpgradeSlots())
        {
            if (tileEntity.isUpgradeValid(upgrade))
            {
                tileEntity.addUpgrade(upgrade);
                return true;
            }
        }

        return false;
    }
}
