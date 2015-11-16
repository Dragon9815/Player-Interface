package net.dragon9815.playerinterfacemod.client.gui;

import net.dragon9815.dragoncore.helpers.LogHelper;
import net.dragon9815.playerinterfacemod.inventory.ContainerPlayerInterface;
import net.dragon9815.playerinterfacemod.reference.Reference;
import net.dragon9815.playerinterfacemod.tileentity.TileEntityPlayerInterface;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiSideConfig extends GuiContainer {
    public static final int GUI_ID = 1;
    private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/side_config.png");
    private int xSize = 176;
    private int ySize = 166;
    private int xPos;
    private int yPos;
    private TileEntityPlayerInterface playerInterface;

    public GuiSideConfig(ContainerPlayerInterface container, TileEntityPlayerInterface playerInterface) {
        super(container);

        this.playerInterface = playerInterface;
    }

    @Override
    public void initGui() {
        super.initGui();

        this.xPos = (width - xSize) / 2;
        this.yPos = (height - ySize) / 2;
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(texture);

        this.drawTexturedModalRect(this.xPos, this.yPos, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        this.drawDefaultBackground();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void mouseClicked(int x, int y, int par3) {
        super.mouseClicked(x, y, par3);

        int mouseX = (x - this.xPos);
        int mouseY = (y - this.yPos);

        if (mouseX > 0 && mouseY > 0 && mouseX <= this.xSize && mouseY <= this.ySize) {
            LogHelper.info(">>> x: " + mouseX + ", y: " + mouseY);
        }
    }
}
