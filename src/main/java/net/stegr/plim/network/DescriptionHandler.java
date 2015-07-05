package net.stegr.plim.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.tileentity.TileEntity;
import net.stegr.plim.PlayerInterfaceMod;
import net.stegr.plim.reference.Reference;
import net.stegr.plim.tileentity.TileEntityPlayerInterface;

@ChannelHandler.Sharable
public class DescriptionHandler extends SimpleChannelInboundHandler<FMLProxyPacket>
{
    public static final String CHANNEL = Reference.MOD_ID + "Description";

    public static void init()
    {
        NetworkRegistry.INSTANCE.newChannel(CHANNEL, new DescriptionHandler());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FMLProxyPacket msg) throws Exception
    {
        ByteBuf buf = msg.payload();

        int x = buf.readInt();
        int y = buf.readInt();
        int z = buf.readInt();

        TileEntity te = PlayerInterfaceMod.proxy.getClientPlayer().worldObj.getTileEntity(x, y, z);
        if(te instanceof TileEntityPlayerInterface)
        {
            ((TileEntityPlayerInterface)te).readFromPacket(buf);
        }
    }
}
