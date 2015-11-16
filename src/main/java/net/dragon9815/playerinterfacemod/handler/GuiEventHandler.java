package net.dragon9815.playerinterfacemod.handler;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.GuiOpenEvent;

public class GuiEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void GuiOpenEvent(GuiOpenEvent event) {
<<<<<<< Updated upstream
        /*if(event.gui instanceof GuiInventory && !(event.gui instanceof GuiInventoryModified)) {
            LogHelper.info(">>> Attempted to open Inventory");

            //GuiInventoryModified guiInventoryModified = new GuiInventoryModified(Minecraft.getMinecraft().thePlayer);

            //Minecraft.getMinecraft().displayGuiScreen(guiInventoryModified);

            event.setCanceled(true);
        }*/
=======

>>>>>>> Stashed changes
    }
}
