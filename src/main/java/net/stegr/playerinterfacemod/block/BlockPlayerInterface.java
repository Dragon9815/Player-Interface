package net.stegr.playerinterfacemod.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.stegr.playerinterfacemod.reference.UpgradeNames;
import net.stegr.playerinterfacemod.tileentity.TileEntityPlayerInterface;
import net.stegr.playerinterfacemod.utility.LogHelper;


public class BlockPlayerInterface extends BlockPlimTileEntityUpgradeable implements IBlockUpgradeable //, IDismantleable
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
                        te.getWorldObj().markBlockForUpdate(te.xCoord, te.yCoord, te.zCoord);
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
        return 5;
    }


    /*@Override // TODO: Fix CoFH dismantle Block
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
    }*/

    @Override
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    @Override
    public int getComparatorInputOverride(World world, int x, int y, int z, int meta)
    {
        TileEntityPlayerInterface te = (TileEntityPlayerInterface)world.getTileEntity(x, y, z);

        if(te != null && te.getBoundPlayer() != null && te.hasUpgrade(UpgradeNames.COMPERATOR) && !world.isRemote)
        {
            return Container.calcRedstoneFromInventory(te.getBoundPlayer().inventory);
        }

        return 0;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
    {
        if(entity instanceof EntityPlayer && !(entity instanceof FakePlayer))
        {
            EntityPlayer player = (EntityPlayer)entity;
            if (world.getTileEntity(x, y, z) instanceof TileEntityPlayerInterface)
            {
                TileEntityPlayerInterface te = (TileEntityPlayerInterface) world.getTileEntity(x, y, z);

                te.bindPlayer(player);
            }
        }
    }
}
