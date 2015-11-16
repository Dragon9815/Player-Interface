package net.dragon9815.playerinterfacemod.integration.tconstruct;

import net.dragon9815.playerinterfacemod.client.gui.GuiTrashInventory;
import net.dragon9815.playerinterfacemod.init.ModItems;
import net.dragon9815.playerinterfacemod.network.PacketHandler;
import net.dragon9815.playerinterfacemod.network.message.MessageOpenGui;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import tconstruct.client.tabs.AbstractTab;

public class TabTrash extends AbstractTab {

    public TabTrash() {
        super(0, 0, 0, new ItemStack(ModItems.TrashItem));
    }

    @Override
    public void onTabClicked() {
        PacketHandler.INSTANCE.sendToServer(new MessageOpenGui(Minecraft.getMinecraft().thePlayer.getUniqueID(), GuiTrashInventory.GUI_ID));
    }

    @Override
    public boolean shouldAddToList() {
        return true;
    }
}
