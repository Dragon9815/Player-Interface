package net.dragon9815.playerinterfacemod.client.settings;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class Keybindings {
    public static KeyBinding openInventory = new KeyBinding("Open Trash Inventory", Keyboard.KEY_G, "Player Interface Mod");

    public static void init() {
        ClientRegistry.registerKeyBinding(openInventory);
    }
}
