package net.dragon9815.playerinterfacemod.handler;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.dragon9815.playerinterfacemod.helpers.LogHelper;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraftforge.client.event.GuiOpenEvent;

public class GuiEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void GuiOpenEvent(GuiOpenEvent event) {
        if(event.gui instanceof GuiInventory) {
            LogHelper.info(">>> Attempted to open Inventory");
            event.setCanceled(true);
        }
    }
}
