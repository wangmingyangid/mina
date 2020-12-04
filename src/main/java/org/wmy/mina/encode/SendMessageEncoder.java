package org.wmy.mina.encode;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;
import org.wmy.mina.pojo.SendMessage;

/**
 *
 * 实现一个多路分离的编解码器--编码器
 * @author wmy
 * @create 2020-12-04 15:24
 */
public class SendMessageEncoder implements MessageEncoder<SendMessage> {
    public void encode(IoSession ioSession, SendMessage sendMessage,
                       ProtocolEncoderOutput protocolEncoderOutput) throws Exception {

        //1个char 2个int ，分配10个字节就够了，也不用自动扩展
        IoBuffer buffer = IoBuffer.allocate(10);
        buffer.putChar(sendMessage.getSymbol());
        buffer.putInt(sendMessage.getI());
        buffer.putInt(sendMessage.getJ());
        buffer.flip();
        protocolEncoderOutput.write(buffer);
    }
}
