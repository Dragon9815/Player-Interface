package net.dragon9815.playerinterfacemod.tileentity;

import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyReceiver;
import cofh.lib.util.helpers.EnergyHelper;
import net.dragon9815.dragoncore.block.BlockUpgradeable;
import net.dragon9815.dragoncore.item.ItemUpgrade;
import net.dragon9815.dragoncore.registry.UpgradeRegistry;
import net.dragon9815.dragoncore.tileentity.TileEntityUpgradeable;
import net.dragon9815.playerinterfacemod.helpers.LogHelper;
import net.dragon9815.playerinterfacemod.helpers.PlayerHelper;
import net.dragon9815.playerinterfacemod.init.ModBlocks;
import net.dragon9815.playerinterfacemod.inventory.InventoryTrash;
import net.dragon9815.playerinterfacemod.reference.UpgradeNames;
import net.dragon9815.playerinterfacemod.registry.InterfaceRegistry;
import net.dragon9815.playerinterfacemod.utility.WrappedInventory;
import net.minecraft.block.BlockRedstoneComparator;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.UUID;

public class TileEntityPlayerInterface extends TileEntityUpgradeable implements ISidedInventory, IEnergyReceiver {
    private static final int BUFFER_SLOTS = 9;
    private static final int TRANSFER_COOLDOWN = 5;
    public ItemStack[] bufferSlots;
    // -------------------------------------------------------
    // SYNC Variables
    public String playerName;
    public boolean isPlayerOnline;
    public byte[][] sideConfiguration;
    protected WrappedInventory playerInventoryPrev;
    // -------------------------------------------------------
    // SERVER only
    private EntityPlayer owner;
    private UUID ownerUUID;
    private boolean isOwnerBound;
    private boolean changed;
    private InventoryTrash inventoryTrash;
    // -------------------------------------------------------
    private boolean isInit;
    private int transferCooldown;
    // -------------------------------------------------------

    public TileEntityPlayerInterface() {
        super();

        owner = null;
        isOwnerBound = false;
        ownerUUID = null;

        bufferSlots = new ItemStack[9];
        playerInventoryPrev = new WrappedInventory(40);

        playerName = null;
        isPlayerOnline = true;

        transferCooldown = TRANSFER_COOLDOWN;

        this.isInit = false;
    }

    @Override
    public BlockUpgradeable getParentBlock() {
        return ModBlocks.player_interface;
    }

    @Override
    public void updateEntityServer() {
        //LogHelper.info(">>> Is Online: " + this.isPlayerOnline);

        if (!isInit) {
            if (isOwnerBound) {
                InterfaceRegistry.addInterface(new InterfaceRegistry.InterfaceDataContainer(this));

                sideConfiguration = new byte[ForgeDirection.VALID_DIRECTIONS.length][40]; // 36 Inventory Slots + 4 Armor Slots (+ 5 Trash Slots) TODO: Add Trash-Slots

                this.isInit = true;
            }
        }

        // Try to bind player until it can
        if (!isOwnerBound) {
            tryToBindPlayer(this.ownerUUID);
        }

        if (owner != null && isPlayerOnline) {
            if (transferCooldown <= 0) {
                transferCooldown = TRANSFER_COOLDOWN;
                this.moveBufferItems();
            }

            if (!WrappedInventory.fromIInventory(owner.inventory).equals(playerInventoryPrev)) {
                changed = true;
                playerInventoryPrev = WrappedInventory.fromIInventory(owner.inventory);
            }
        }

        if (changed) {
            this.onChange();
        }

        transferCooldown--;
    }

    @Override
    public void updateEntityClient() {
        //LogHelper.info(">>> Owner[Client]: " + this.owner);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
    }

    private void moveBufferItems() {
        if (this.hasUpgrade(UpgradeNames.ITEMTRANSFER) && this.hasUpgrade(UpgradeNames.ITEMTRANSFER) && this.isPlayerOnline) {
            int var1 = firstStackInBuffer();
            if (var1 != -1) {
                ItemStack stack;

                stack = bufferSlots[var1];

                owner.inventory.addItemStackToInventory(stack);

                //LogHelper.info(">>> Stacksize: " + stack.stackSize);

                if (bufferSlots[var1].stackSize <= 0) {
                    bufferSlots[var1] = null;
                }
            }
        }
    }

    public void dropAllItemsInBuffer(World world, int x, int y, int z) {
        for (ItemStack itemstack : this.bufferSlots) {
            if (itemstack != null) {
                float f = world.rand.nextFloat() * 0.8F + 0.1F;
                float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
                float f2 = world.rand.nextFloat() * 0.8F + 0.1F;

                while (itemstack.stackSize > 0) {
                    int j = world.rand.nextInt(21) + 10;

                    if (j > itemstack.stackSize) {
                        j = itemstack.stackSize;
                    }

                    itemstack.stackSize -= j;
                    EntityItem entityItem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));

                    if (itemstack.hasTagCompound()) {
                        entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                    }

                    float f3 = 0.05F;

                    entityItem.motionX = (double) ((float) world.rand.nextGaussian() * f3);
                    entityItem.motionY = (double) ((float) world.rand.nextGaussian() * f3 + 0.2F);
                    entityItem.motionZ = (double) ((float) world.rand.nextGaussian() * f3);

                    world.spawnEntityInWorld(entityItem);
                }
            }

        }
    }

    private void onChange() {
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            if (worldObj.getBlock(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ) instanceof BlockRedstoneComparator) {
                worldObj.notifyBlockOfNeighborChange(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ, worldObj.getBlock(xCoord, yCoord, zCoord));
            }
        }

        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

        changed = false;
    }

    private int firstStackInBuffer() {
        if (this.hasUpgrade(UpgradeNames.ITEMTRANSFER)) {
            for (int i = 0; i < this.bufferSlots.length; ++i) {
                if (this.bufferSlots[i] != null) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public int getSizeInventory() {
        int size = 0;
        if (this.hasUpgrade(UpgradeNames.ITEMTRANSFER)) {
            size += this.bufferSlots.length;
        }

        if (owner != null) {
            size += owner.inventory.getSizeInventory();
        }

        return size;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        ItemStack var1 = null;

        if (!this.hasUpgrade(UpgradeNames.ITEMTRANSFER)) {
            return null;
        }

        if (slot < this.bufferSlots.length) {
            var1 = bufferSlots[slot];
        }
        else if (this.hasOwner()) {
            var1 = owner.inventory.getStackInSlot(slot - ((this.hasUpgrade(UpgradeNames.ITEMTRANSFER)) ? this.bufferSlots.length : 0));
        }

        return var1;
    }

    @Override
    public ItemStack decrStackSize(int slot, int num) {
        if (!this.hasUpgrade(UpgradeNames.ITEMTRANSFER)) {
            return null;
        }

        //ItemStack[] itemStacks = this.bufferSlots;

        if (slot < BUFFER_SLOTS) {
            return null;
            /*if (itemStacks[slot] != null)
            {
                ItemStack stack;

                if (itemStacks[slot].stackSize <= num)
                {
                    stack = itemStacks[slot];
                    itemStacks[slot] = null;
                    return stack;
                }
                else
                {
                    stack = itemStacks[slot].splitStack(num);

                    if (itemStacks[slot].stackSize == 0)
                    {
                        itemStacks[slot] = null;
                    }

                    return stack;
                }
            }*/
        }

        if (this.hasOwner()) {
            return owner.inventory.decrStackSize(slot - ((this.hasUpgrade(UpgradeNames.ITEMTRANSFER)) ? this.bufferSlots.length : 0), num);
        }

        changed = true;
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        ItemStack[] itemStacks = this.bufferSlots;

        if (slot < BUFFER_SLOTS && this.hasUpgrade(UpgradeNames.ITEMTRANSFER)) {
            itemStacks[slot] = itemStack;
        }

        /*else
        {
            if (owner != null && this.hasUpgrade(UpgradeNames.ITEMTRANSFER))
            {
                owner.inventory.setInventorySlotContents(slot - ((this.hasUpgrade(UpgradeNames.ITEMTRANSFER)) ? this.bufferSlots.length : 0), itemStack);
            }
        }*/

        changed = true;
    }

    @Override
    public String getInventoryName() {
        return "PlayerInterface";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return false;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        if (!this.hasUpgrade(UpgradeNames.ITEMTRANSFER)) {
            return false;
        }

        return slot < BUFFER_SLOTS;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        //LogHelper.info(">>> Save UUID: " + ((owner != null) ? owner.getUniqueID().toString() : ""));

        tag.setString("owner", (this.ownerUUID != null) ? this.ownerUUID.toString() : "");

        if (playerName != null && playerName.length() > 0) {
            tag.setString("playerName", this.playerName);
        }

        NBTTagList tagList = new NBTTagList();

        for (int i = 0; i < bufferSlots.length; i++) {
            NBTTagCompound tag1 = new NBTTagCompound();

            if (bufferSlots[i] != null) {
                bufferSlots[i].writeToNBT(tag1);
            }

            tagList.appendTag(tag1);
        }

        tag.setTag("Buffer_Slots", tagList);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        String uuid = tag.getString("owner");
        //LogHelper.info(">>> Read UUID: " + uuid);

        if (!uuid.equals("")) {
            this.bindPlayer(UUID.fromString(uuid));
        }
        else {
            this.owner = null;
        }

        String name = tag.getString("playerName");
        this.playerName = (name.equals("")) ? null : name;

        if (tag.hasKey("Buffer_Slots")) {
            NBTTagList tagList = tag.getTagList("Buffer_Slots", 10);

            for (int i = 0; i < tagList.tagCount() && i < bufferSlots.length; i++) {
                NBTTagCompound tag1 = tagList.getCompoundTagAt(i);

                bufferSlots[i] = ItemStack.loadItemStackFromNBT(tag1);
            }
        }
    }

    @Override
    // TODO: Add Code for Configuration-Tool
    public int[] getAccessibleSlotsFromSide(int side) {
        int numSlots = 0;
        int startSlot = 0;

        if (side == ForgeDirection.DOWN.ordinal()) {
            numSlots = (owner == null) ? 0 : owner.inventory.getSizeInventory();
            startSlot = BUFFER_SLOTS;
        }
        else {
            numSlots = BUFFER_SLOTS;
            startSlot = 0;
        }

        int[] slots = new int[numSlots];

        for (int i = 0; i < numSlots; i++) {
            slots[i] = i + startSlot;
        }

        return slots;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack item, int side) {
        return this.hasUpgrade(UpgradeNames.ITEMTRANSFER) && slot < BUFFER_SLOTS && side != ForgeDirection.DOWN.ordinal();
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack item, int side) {
        return this.owner != null && this.hasUpgrade(UpgradeNames.ITEMTRANSFER) && slot > BUFFER_SLOTS && side == ForgeDirection.DOWN.ordinal();
    }

    @Override
    public void onUpgrade(ItemUpgrade upgrade) {
        changed = true;
    }

    public void bindPlayer(UUID playerUUID) {
        this.changed = true;

        if (playerUUID == null) {
            this.owner = null;
            return;
        }

        this.ownerUUID = playerUUID;
        this.isOwnerBound = false;
    }

    public void bindPlayer(String playerUUIDString) {
        this.changed = true;

        if (playerUUIDString != null) {
            try {
                UUID uuid = UUID.fromString(playerUUIDString);
                this.tryToBindPlayer(uuid);
                return;
            }
            catch (IllegalArgumentException ex) {
                LogHelper.info(">>> My UUID thingy broke: ");
                ex.printStackTrace();
            }
        }

        this.owner = null;
    }

    private void tryToBindPlayer(UUID playerUUID) {
        if (playerUUID == null) {
            return;
        }

        this.owner = PlayerHelper.getPlayer(playerUUID);

        this.isOwnerBound = (this.owner != null);

        if (isOwnerBound) {
            this.setPlayerOnline();
            this.playerName = this.owner.getDisplayName();
        }
    }

    public void setPlayerOnline() {
        isPlayerOnline = true;
        this.changed = true;
    }

    public void setPlayerOffline() {
        isPlayerOnline = false;
        this.owner = null;
        this.changed = true;
    }

    public EntityPlayer getOwner() {
        return this.owner;
    }

    public boolean hasOwner() {
        return this.owner != null;
    }

    public void writeToSyncNBT(NBTTagCompound tag) {
        super.writeToSyncNBT(tag);

        if (playerName != null && playerName.length() > 0) {
            tag.setString("playerName", this.playerName);
        }
        tag.setBoolean("isPlayerOnline", this.isPlayerOnline);
    }

    public void readFromSyncNBT(NBTTagCompound tag) {
        super.readFromSyncNBT(tag);

        String name = tag.getString("playerName");

        this.playerName = (name.equals("")) ? null : name;
        this.isPlayerOnline = tag.getBoolean("isPlayerOnline");
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.readFromSyncNBT(pkt.func_148857_g());
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();

        this.writeToSyncNBT(tag);

        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return this.getUpgradeInventory().hasUpgrade(UpgradeRegistry.instance().getUpgrade(UpgradeNames.RFTRANSFER));
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        int energyLeft = maxReceive;
        int received = 0;

        if (this.getUpgradeInventory().hasUpgrade(UpgradeRegistry.instance().getUpgrade(UpgradeNames.RFTRANSFER))) {
            //received = this.energyStorage.receiveEnergy(energyLeft, simulate);
            energyLeft = chargePlayerItems(energyLeft, simulate);
        }

        return maxReceive - energyLeft;

        //return 0;
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        //return this.energyStorage.getEnergyStored();
        return 1;
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        //return this.energyStorage.getMaxEnergyStored();
        return 1;
    }

    private int chargePlayerItems(int maxEnergy, boolean simulate) {
        int energyLeft = maxEnergy;

        if (owner != null) {
            for (ItemStack itemStack : owner.inventory.mainInventory) {
                if (EnergyHelper.isEnergyContainerItem(itemStack)) {
                    int transfered = ((IEnergyContainerItem) itemStack.getItem()).receiveEnergy(itemStack, energyLeft, simulate);

                    //LogHelper.info(">>> Transferred " + transfered + "RF to " + itemStack.getDisplayName() + "; simulated: " + simulate);

                    energyLeft -= transfered;

                    if (energyLeft <= 0) {
                        break;
                    }
                }
            }

            if (energyLeft <= 0) {
                return 0;
            }

            for (ItemStack itemStack : owner.inventory.armorInventory) {
                if (EnergyHelper.isEnergyContainerItem(itemStack)) {
                    int transfered = ((IEnergyContainerItem) itemStack.getItem()).receiveEnergy(itemStack, energyLeft, simulate);

                    //LogHelper.info(">>> Transferred " + transfered + "RF to " + itemStack.getDisplayName() + "; simulated: " + simulate);

                    energyLeft -= transfered;

                    if (energyLeft <= 0) {
                        break;
                    }
                }
            }
        }

        return Math.max(energyLeft, 0);
    }
}
