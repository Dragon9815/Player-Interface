package net.dragon9815.playerinterfacemod.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.dragon9815.playerinterfacemod.inventory.ContainerTrash;
import net.dragon9815.playerinterfacemod.integration.ModRegistry;
import net.dragon9815.playerinterfacemod.integration.tconstruct.TabTrash;
import net.dragon9815.playerinterfacemod.inventory.InventoryTrash;
import net.dragon9815.playerinterfacemod.reference.Reference;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.InvocationTargetException;

@SideOnly(Side.CLIENT)
public class GuiTrashInventory extends InventoryEffectRenderer {
    public static final int GUI_ID = 2;
    private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/trash_inventory.png");
    public InventoryPlayer invPlayer;
    public InventoryTrash invTrash;
    private int xSize = 176;
    private int ySize = 166;
    private int xPos;
    private int yPos;

    public GuiTrashInventory(InventoryPlayer inventoryPlayer, InventoryTrash inventoryTrash) {
        super(new ContainerTrash(inventoryPlayer, inventoryTrash));

        this.invPlayer = inventoryPlayer;
        this.invTrash = inventoryTrash;
    }

    @Override
    public void initGui() {
        this.xPos = (width - xSize) / 2;
        this.yPos = (height - ySize) / 2;
        this.buttonList.clear();

        if (ModRegistry.TConstruct.isLoaded() && ModRegistry.TConstruct.addTabs != null && ModRegistry.TConstruct.updateTab != null) {
            try {
                ModRegistry.TConstruct.updateTab.invoke(null, xPos, yPos, TabTrash.class);
                ModRegistry.TConstruct.addTabs.invoke(null, this.buttonList);
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        super.initGui();
    }

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        super.drawScreen(par1, par2, par3);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        this.mc.renderEngine.bindTexture(texture);

        this.drawTexturedModalRect(this.xPos, this.yPos, 0, 0, xSize, ySize);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
