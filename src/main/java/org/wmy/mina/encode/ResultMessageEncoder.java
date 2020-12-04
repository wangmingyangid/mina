package org.wmy.mina.encode;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;
import org.wmy.mina.pojo.ResultMessage;

/**
 * @author wmy
 * @create 2020-12-04 15:45
 */
public class ResultMessageEncoder implements MessageEncoder<ResultMessage> {
    public void encode(IoSession ioSession, ResultMessage resultMessage,
                       ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        IoBuffer buffer = IoBuffer.allocate(4);
        buffer.putInt(resultMessage.getResult());
        buffer.flip();
        protocolEncoderOutput.write(buffer);
    }
}
