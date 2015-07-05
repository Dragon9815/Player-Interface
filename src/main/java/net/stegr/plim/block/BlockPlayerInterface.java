package net.stegr.plim.block;

import cofh.api.block.IDismantleable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
import net.stegr.plim.utility.UpgradeRegistry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class BlockPlayerInterface extends BlockPlimTileEntityUpgradeable implements IBlockUpgradeable, IDismantleable
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
        if(!super.onBlockActivated(world, x, y, z, player, meta, par7, par8, par9) && !world.isRemote)
        {
            TileEntityPlayerInterface te = (TileEntityPlayerInterface) world.getTileEntity(x, y, z);

            if (!(player instanceof FakePlayer))
            {
                if (player.isSneaking())
                {
                    if (te.getBoundPlayer() != null && te.getBoundPlayer().getUniqueID().equals(player.getUniqueID()))
                    {
                        te.bindPlayer(null);
                        //te.markDirty();
                        //te.getDescriptionPacket();
                        te.getWorldObj().markBlockForUpdate(te.xCoord, te.yCoord, te.zCoord);
                        te.markDirty();
                        if(!world.isRemote)
                        {
                            player.addChatMessage(new ChatComponentText("Player unbound"));
                            LogHelper.info("Player unbound");
                        }
                    }
                }
                else
                {
                    if (te.getBoundPlayer() == null)
                    {
                        te.bindPlayer(player);
                        te.getWorldObj().markBlockForUpdate(te.xCoord, te.yCoord, te.zCoord);
                        te.markDirty();
                        if(!world.isRemote)
                        {
                            player.addChatMessage(new ChatComponentText("Player bound"));
                            LogHelper.info("Player bound: " + player.getUniqueID().toString());
                        }
                    }
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
    public ArrayList<ItemStack> dismantleBlock(EntityPlayer player, World world, int x, int y, int z, boolean returnDrops)
    {
        ArrayList<ItemStack> retStacks = new ArrayList<ItemStack>();
        Map<String, Integer> upgrades;
        TileEntity te = world.getTileEntity(x, y, z);

        if(te instanceof  TileEntityPlayerInterface)
        {
            TileEntityPlayerInterface playerInterface = (TileEntityPlayerInterface)te;

            upgrades = playerInterface.installedUpgrades;
            Set<String> keySet = upgrades.keySet();
            Iterator<String> it1 = keySet.iterator();

            while(it1.hasNext())
            {
                String name = it1.next();

                ItemUpgrade item = (ItemUpgrade)UpgradeRegistry.getUpgrade(name);

                retStacks.add(new ItemStack(item, upgrades.get(name)));
            }
        }

        return retStacks;
    }

    @Override
    public boolean canDismantle(EntityPlayer player, World world, int x, int y, int z)
    {
        return true;
    }
}
