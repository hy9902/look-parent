package jvm;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author hy9902
 * @create 2018-10-10 16:48
 */
public class FixedLengthFrameDecoder extends ByteToMessageDecoder {

    private final int frameLength;

    public FixedLengthFrameDecoder(int frameLength){
        if(frameLength <= 0){
            throw new IllegalArgumentException("frameLength  必须是正整数:"+ frameLength);
        }
        this.frameLength = frameLength;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        /*while (in.readableBytes() >= frameLength){
            ByteBuf byteBuf = in.readBytes(frameLength);
            out.add(byteBuf);
        }*/
        System.out.println("可读:"+in.readableBytes()+", 容量:" + in.capacity());
        while (in.isReadable()){
            ByteBuf temp =  in.readBytes(4);
            System.out.println("可读:"+in.readableBytes());
            int m = temp.getInt(0);
            System.out.println(m);
            out.add(m);
        }
        System.out.println("out size : " + out.size());
    }
}
