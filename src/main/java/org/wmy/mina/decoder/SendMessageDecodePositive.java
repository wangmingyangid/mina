package org.wmy.mina.decoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;
import org.wmy.mina.pojo.SendMessage;

/**
 * 实现一个多路分离的编解码器---解码器1
 * @author wmy
 * @create 2020-12-04 15:32
 */
public class SendMessageDecodePositive implements MessageDecoder {

    /**
     * 判断该解码器是否适用
     * decodable()方法对参数IoBuffer ioBuffer 的任何操作在方法结束之后，都会复原
     *
     * @param ioSession
     * @param ioBuffer
     * @return
     */
    public MessageDecoderResult decodable(IoSession ioSession, IoBuffer ioBuffer) {
        //返回limit-position,返回缓冲器中的剩余字节
        if(ioBuffer.remaining() <2 ){
            return MessageDecoderResult.NEED_DATA;
        }
        char symbol = ioBuffer.getChar();
        if('+' == symbol){
            return MessageDecoderResult.OK;
        }else{
            return MessageDecoderResult.NOT_OK;
        }

    }

    public MessageDecoderResult decode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        SendMessage sm = new SendMessage();
        sm.setSymbol(ioBuffer.getChar());
        sm.setI(ioBuffer.getInt());
        sm.setJ(ioBuffer.getInt());
        protocolDecoderOutput.write(sm);

        return MessageDecoderResult.OK;
    }

    public void finishDecode(IoSession ioSession, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

    }
}
