package net.dragon9815.playerinterfacemod.handler;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.GuiOpenEvent;

public class GuiEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void GuiOpenEvent(GuiOpenEvent event) {
    }
}
