package me.zhengjie.nio.netty.udp.sever2.decoder;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import me.zhengjie.modules.performance.domain.PerformanceDataTrashcan;

import java.util.List;

public class MessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        if (byteBuf.readableBytes() < 2) {
            return;
        }

        Gson gson = new Gson();
        short jsonBytesLength = (short) (byteBuf.readShort() - 2);
        short type = byteBuf.readShort();

        byte[] tmp = new byte[jsonBytesLength];
        byteBuf.readBytes(tmp);
        String json = new String(tmp, "UTF-8");

        Class<?> clazz = PerformanceDataTrashcan.class;
        Object msgObj = gson.fromJson(json, clazz);
        list.add(msgObj);

    }
}
