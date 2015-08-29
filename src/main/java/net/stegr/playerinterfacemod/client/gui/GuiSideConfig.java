package net.stegr.playerinterfacemod.client.gui;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.stegr.playerinterfacemod.reference.Reference;
import net.stegr.playerinterfacemod.tileentity.TileEntityPlayerInterface;
import net.stegr.repackage.cofh.lib.gui.GuiBase;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.util.Map;

public class GuiSideConfig extends GuiScreen
{
    public static final int GUI_ID = 1;

    private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID, "textures/gui/side_config.png");

    @Override
    public void drawScreen(int par1, int par2, float par3) {
        this.drawDefaultBackground();
    }
}
