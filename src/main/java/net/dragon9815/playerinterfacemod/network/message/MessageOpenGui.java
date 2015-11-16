package net.dragon9815.playerinterfacemod.network.message;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.dragon9815.dragoncore.util.Platform;
import net.dragon9815.playerinterfacemod.PlayerInterfaceMod;
import net.dragon9815.playerinterfacemod.helpers.LogHelper;
import net.dragon9815.playerinterfacemod.helpers.PlayerHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import java.util.UUID;

public class MessageOpenGui implements IMessage, IMessageHandler<MessageOpenGui, MessageOpenGui> {

    public UUID playerUUID;
    public int guiID;

    public MessageOpenGui() {
    }

    public MessageOpenGui(UUID playerUUID, int guiID) {
        this.playerUUID = playerUUID;
        this.guiID = guiID;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        NBTTagCompound tagCompound = ByteBufUtils.readTag(buf);

        try {
            this.playerUUID = UUID.fromString(tagCompound.getString("playerUUID"));
            this.guiID = tagCompound.getInteger("guiID");
        }
        catch (IllegalArgumentException ex) {
            LogHelper.info("Converting UUID from Bytes failed.");
            ex.printStackTrace();
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        NBTTagCompound tagCompound = new NBTTagCompound();

        tagCompound.setString("playerUUID", this.playerUUID.toString());
        tagCompound.setInteger("guiID", this.guiID);

        ByteBufUtils.writeTag(buf, tagCompound);
    }

    @Override
    public MessageOpenGui onMessage(MessageOpenGui message, MessageContext ctx) {
        if (Platform.isClient()) {
            return null;
        }

        if (message.playerUUID == null) {
            return null;
        }

        EntityPlayer player = PlayerHelper.getPlayer(message.playerUUID);

        if (player == null) {
            return null;
        }

        player.openGui(PlayerInterfaceMod.instance, message.guiID, null, 0, 0, 0);

        return null;
    }
}
