package net.stegr.playerinterfacemod.block;

import cofh.api.block.IDismantleable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.dragon9815.dragoncore.block.BlockUpgradeable;
import net.dragon9815.dragoncore.registry.UpgradeRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.ForgeDirection;
import net.stegr.playerinterfacemod.creativetab.CreativeTabPlim;
import net.stegr.playerinterfacemod.reference.MachineNames;
import net.stegr.playerinterfacemod.reference.Reference;
import net.stegr.playerinterfacemod.reference.UpgradeNames;
import net.stegr.playerinterfacemod.tileentity.TileEntityPlayerInterface;
import net.stegr.playerinterfacemod.utility.Platform;

import java.util.ArrayList;
import java.util.Random;


public class BlockPlayerInterface extends BlockUpgradeable implements IDismantleable {
    public BlockPlayerInterface() {
        super(Material.iron);
        this.setBlockName(MachineNames.PLAYER_INTERFACE);
        this.setCreativeTab(CreativeTabPlim.PLIM_TAB);
        this.setBlockTextureName("");

        //this.setHarvestLevel("iron", 2);

        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setStepSound(soundTypeMetal);
    }

    @Override
    public String getModID() {
        return Reference.MOD_ID;
    }

    @Override
    public int getNumUpgradeSlots() {
        return 5;
    }

    @Override
    public ItemStack[] getValidUpgrades() {
        return new ItemStack[]{new ItemStack(UpgradeRegistry.instance().getUpgrade(UpgradeNames.ITEMTRANSFER), 1), new ItemStack(UpgradeRegistry.instance().getUpgrade(UpgradeNames.RFTRANSFER), 1), new ItemStack(UpgradeRegistry.instance().getUpgrade(UpgradeNames.COMPERATOR), 1)};
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float par7, float par8, float par9) {
        /*if(!super.onBlockActivated(world, x, y, z, player, meta, par7, par8, par9) && !world.isRemote)
        {
           TileEntityPlayerInterface te = (TileEntityPlayerInterface) world.getTileEntity(x, y, z);

            if (!(player instanceof FakePlayer))
            {
                if (player.isSneaking())
                {
                    if (te.getOwner() != null && te.getOwner().getUniqueID().equals(player.getUniqueID()))
                    {
                        te.bindPlayer((EntityPlayer)null);
                        te.getWorldObj().markBlockForUpdate(te.xCoord, te.yCoord, te.zCoord);
                        if(!world.isRemote)
                        {
                            player.addChatMessage(new ChatComponentText(StringHelper.LIGHT_BLUE + ("" + StringHelper.localize("message.playerUnbound"))));
                            LogHelper.info("Player unbound");
                        }
                    }
                }
                else
                {
                    if (te.getOwner() == null)
                    {
                        te.bindPlayer(player);
                        te.getWorldObj().markBlockForUpdate(te.xCoord, te.yCoord, te.zCoord);
                        if(!world.isRemote)
                        {
                            player.addChatMessage(new ChatComponentText(StringHelper.LIGHT_BLUE + ("" + StringHelper.localize("message.playerBound") + ": " + player.getDisplayName())));
                            LogHelper.info("Player bound: " + player.getUniqueID().toString());
                        }
                    }
                }
            }

        }

        return true;*/

        return super.onBlockActivated(world, x, y, z, player, meta, par7, par8, par9);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityPlayerInterface();
    }

    @Override // TODO: Fix CoFH dismantle Block
    public ArrayList<ItemStack> dismantleBlock(EntityPlayer player, World world, int x, int y, int z, boolean returnDrops)
    {
        /*ArrayList<ItemStack> retStacks = new ArrayList<ItemStack>();
        ItemStack[] upgrades;
        TileEntity te = world.getTileEntity(x, y, z);

        if(te instanceof  TileEntityPlayerInterface)
        {
            TileEntityPlayerInterface playerInterface = (TileEntityPlayerInterface)te;

            upgrades = playerInterface.getUpgradeInventory().getInstalledUpgrades();

            for(ItemStack upgrade : upgrades)
            {
                retStacks.add(upgrade);
            }
        }

        return retStacks;*/
        return new ArrayList<ItemStack>();
    }

    @Override
    public boolean canDismantle(EntityPlayer player, World world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(World world, int x, int y, int z, int meta) {
        TileEntityPlayerInterface te = (TileEntityPlayerInterface) world.getTileEntity(x, y, z);

        if (te != null && te.getOwner() != null && te.hasUpgrade(UpgradeNames.COMPERATOR) && !world.isRemote) {
            return Container.calcRedstoneFromInventory(te.getOwner().inventory);
        }

        return 0;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        if (Platform.isClient()) {
            return;
        }

        if (entity instanceof EntityPlayer && !(entity instanceof FakePlayer)) {
            EntityPlayer player = (EntityPlayer) entity;
            if (world.getTileEntity(x, y, z) instanceof TileEntityPlayerInterface) {
                TileEntityPlayerInterface te = (TileEntityPlayerInterface) world.getTileEntity(x, y, z);

                te.bindPlayer(player.getUniqueID());
                te.setPlayerOnline();
            }
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        TileEntityPlayerInterface te = (TileEntityPlayerInterface) world.getTileEntity(x, y, z);

        te.dropAllItemsInBuffer(world, x, y, z);

        super.breakBlock(world, x, y, z, block, meta);
    }

    public int getRenderType() {
        return -1;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getLightOpacity() {
        return 0;
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return (side == ForgeDirection.UP || side == ForgeDirection.DOWN);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        /*TileEntityPlayerInterface te = (TileEntityPlayerInterface)world.getTileEntity(x, y, z);

        if(te != null && te.transfered && te.getBoundPlayer() != null)
        {
            double motionX = te.getBoundPlayer().serverPosX - x;
            double motionY = te.getBoundPlayer().serverPosY - y;
            double motionZ = te.getBoundPlayer().serverPosZ - z;
            Vector3d vec = new Vector3d(0.0F, 1F, 0.0F);

            world.spawnParticle("portal", te.getBoundPlayer().serverPosX, te.getBoundPlayer().serverPosY, te.getBoundPlayer().serverPosZ, motionX, motionY, motionZ);
            world.spawnParticle("portal", x + 0.5F, y + 1.1F + 0.5F * random.nextFloat(), z + 0.5F, vec.x, vec.y, vec.z);
            world.spawnParticle("portal", x + 0.5F, y + 1.1F + 0.5F * random.nextFloat(), z + 0.5F, vec.x, vec.y, vec.z);
            world.spawnParticle("portal", x + 0.5F, y + 1.1F + 0.5F * random.nextFloat(), z + 0.5F, vec.x, vec.y, vec.z);

            //LogHelper.info("spawn");

            //te.transfered = false;
        }*/
    }
}
