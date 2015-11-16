package net.dragon9815.playerinterfacemod.proxy;

import cpw.mods.fml.common.network.IGuiHandler;
import net.dragon9815.playerinterfacemod.client.gui.GuiTrashInventory;
import net.dragon9815.playerinterfacemod.inventory.ContainerTrash;
import net.dragon9815.playerinterfacemod.inventory.InventoryTrash;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class CommonProxy implements IProxy, IGuiHandler {
    public abstract EntityPlayer getClientPlayer();

    public void intermodComm() {
    }

<<<<<<< Updated upstream
    public void registerRenderers() {
    }

=======
>>>>>>> Stashed changes
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GuiTrashInventory.GUI_ID) {
            return new ContainerTrash(player.inventory, new InventoryTrash(player));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GuiTrashInventory.GUI_ID) {
            return new GuiTrashInventory(player.inventory, new InventoryTrash(player));
        }

        return null;
    }

    public void registerClientStuff() {
    }

    public void registerServerStuff() {
    }
}
