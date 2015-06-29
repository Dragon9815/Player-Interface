package net.stegr.plim.block;

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


public class BlockPlayerInterface extends BlockPlimTileEntity implements IBlockUpgradeable
{
    public BlockPlayerInterface()
    {
        super();
        this.setBlockName("pl_interface");
        this.setBlockTextureName("pl_interface");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        TileEntityPlayerInterface te = (TileEntityPlayerInterface)world.getTileEntity(x, y, z);

        if(player.getHeldItem().getItem() instanceof ItemUpgrade)
        {
            ItemUpgrade item = (ItemUpgrade)player.getHeldItem().getItem();

            if(te instanceof TileEntityUpgradeable)
            {
                TileEntityUpgradeable teup = (TileEntityUpgradeable)te;

                if(teup.isUpgradeValid(item))
                {
                    teup.addUpgrade(item);
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
                    ChatComponentStyle s = new ChatComponentStyle()
                    {
                        @Override
                        public String getUnformattedTextForChat()
                        {
                            return "Player Unbound";
                        }

                        @Override
                        public IChatComponent createCopy()
                        {
                            return this;
                        }
                    };
                    player.addChatMessage(new ChatComponentText("Player unbound"));
                    System.out.println("Player unbound");
                }
            }
            else
            {
                if(te.boundPlayer == null)
                {
                    te.boundPlayer = player;
                    te.markDirty();
                    player.addChatMessage(new ChatComponentText("Player bound"));
                    System.out.println("Player bound: " + player.getUniqueID().toString());
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
    public void GetMaxUpgrades()
    {

    }

    @Override
    public void doUpgrade(IUpgrade upgrade)
    {

    }
}
