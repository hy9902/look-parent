package jvm;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author hy9902
 * @create 2018-10-10 16:47
 */
public class NettyTest {

    @Test
    public void testInBound() {
        ByteBuf buf = Unpooled.buffer();
        for(int i=0; i<9; i++){
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        assertTrue(channel.writeInbound(input.retain()));
        assertTrue(channel.finish());
    }
}
