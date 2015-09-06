package net.stegr.playerinterfacemod.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.stegr.playerinterfacemod.reference.Reference;

public class GuiSideConfig extends GuiScreen {
    public static final int GUI_ID = 1;

    private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/side_config.png");

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();
    }
}
