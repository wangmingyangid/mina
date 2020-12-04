package org.wmy.mina.decoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;
import org.wmy.mina.pojo.ResultMessage;

/**
 * @author wmy
 * @create 2020-12-04 15:49
 */
public class ResultMessageDecoder implements MessageDecoder {

    public MessageDecoderResult decodable(IoSession ioSession, IoBuffer ioBuffer) {
        if (ioBuffer.remaining() < 4){
            return MessageDecoderResult.NEED_DATA;
        }else if (ioBuffer.remaining() == 4){
            return MessageDecoderResult.OK;
        }else {
            return MessageDecoderResult.NOT_OK;
        }
    }

    public MessageDecoderResult decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setResult(ioBuffer.getInt());
        protocolDecoderOutput.write(resultMessage);
        return MessageDecoderResult.OK;
    }

    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

    }
}
