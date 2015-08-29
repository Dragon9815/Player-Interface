package net.stegr.playerinterfacemod.registry;

import net.minecraft.item.ItemStack;
import net.stegr.playerinterfacemod.block.BlockTileEntityUpgradeable;
import net.stegr.playerinterfacemod.block.IBlockUpgradeable;
import net.stegr.playerinterfacemod.helpers.LogHelper;
import net.stegr.playerinterfacemod.item.ItemUpgrade;

import java.util.*;

public class UpgradeRegistry
{
    private List<ItemUpgrade> registeredUpgrades;
    private Map<String, List<ItemStack>> validMachineUpgrades;
    private Map<String, IBlockUpgradeable> registeredMachines;
    private static UpgradeRegistry INSTANCE = null;

    public static UpgradeRegistry instance()
    {
        if(INSTANCE == null)
        {
            INSTANCE = new UpgradeRegistry();
        }

        return INSTANCE;
    }

    public UpgradeRegistry()
    {
        registeredUpgrades = new LinkedList<ItemUpgrade>();
        validMachineUpgrades = new HashMap<String, List<ItemStack>>();
        registeredMachines = new HashMap<String, IBlockUpgradeable>();
    }


    public void registerUpgrade(ItemUpgrade upgrade)
    {
        if(!isRegistered(upgrade.getUpgradeID()))
        {
            registeredUpgrades.add(upgrade);
        }
        else
        {
            LogHelper.warn("Upgrade already registered! " + upgrade.getUpgradeID());
        }
    }

    public boolean isRegistered(String upgradeID)
    {
        return getUpgrade(upgradeID) != null;
    }

    public ItemUpgrade getUpgrade(String upgradeID)
    {
        Iterator<ItemUpgrade> it = registeredUpgrades.iterator();

        while(it.hasNext())
        {
            ItemUpgrade upgrade = it.next();

            if(upgrade.getUpgradeID().equals(upgradeID))
            {
                return upgrade;
            }
        }

        return null;
    }

    public void registerMachine(IBlockUpgradeable machine, String name)
    {
        if(!this.registeredMachines.containsKey(name))
        {
            this.registeredMachines.put(name, machine);
        }
        else
        {
            LogHelper.warn("Machine already registered! " + ((BlockTileEntityUpgradeable) machine).getLocalizedName());
        }
    }

    public IBlockUpgradeable getMachine(String name)
    {
        return this.registeredMachines.get(name);
    }

    public void addValidUpgradeToMachine(String name, ItemUpgrade upgrade, int num)
    {
        ItemStack upgradeStack = new ItemStack(upgrade, num);

        List<ItemStack> upgradeStacks = this.validMachineUpgrades.get(name);

        if(upgradeStacks == null)
            upgradeStacks = new ArrayList<ItemStack>();

        upgradeStacks.add(upgradeStack);

        this.validMachineUpgrades.put(name, upgradeStacks);
    }

    public void addValidUpgradesToMachine(String name, ItemStack[] upgrades)
    {
        List<ItemStack> upgradeStacks = this.validMachineUpgrades.get(name);

        if(upgradeStacks == null)
            upgradeStacks = new ArrayList<ItemStack>();

        for(ItemStack upgradeStack : upgrades)
        {
            upgradeStacks.add(upgradeStack);
        }

        this.validMachineUpgrades.put(name, upgradeStacks);
    }

    public ItemStack[] getValidUpgradesForMachine(String name)
    {
        List<ItemStack> upgradeStacks = this.validMachineUpgrades.get(name);

        if(upgradeStacks == null)
            upgradeStacks = new ArrayList<ItemStack>();

        return upgradeStacks.toArray(new ItemStack[upgradeStacks.size()]);
    }

    public int getNumUpgradesValidForMachine(String upgradeName, String machineName)
    {
        if(!this.isRegistered(upgradeName))
            return 0;

        ItemStack[] stacks = this.getValidUpgradesForMachine(machineName);

        for(ItemStack stack : stacks)
        {
            if(stack.isItemEqual(new ItemStack(this.getUpgrade(upgradeName))))
            {
                return stack.stackSize;
            }
        }

        return 0;
    }
}
