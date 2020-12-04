package org.wmy.mina.factory;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;
import org.wmy.mina.decoder.ResultMessageDecoder;
import org.wmy.mina.decoder.SendMessageDecodePositive;
import org.wmy.mina.decoder.SendMessageDecoderNegative;
import org.wmy.mina.encode.ResultMessageEncoder;
import org.wmy.mina.encode.SendMessageEncoder;
import org.wmy.mina.pojo.ResultMessage;
import org.wmy.mina.pojo.SendMessage;

/**
 * 根据标识，创建适用于客户端和服务端的协议编解码工厂
 * @author wmy
 * @create 2020-12-04 15:57
 */
public class MathProtocolCodecFactory extends DemuxingProtocolCodecFactory {
    public MathProtocolCodecFactory() {
    }
    public MathProtocolCodecFactory(boolean server) {
        if(server){
            super.addMessageEncoder(ResultMessage.class,ResultMessageEncoder.class);
            super.addMessageDecoder(SendMessageDecodePositive.class);
            super.addMessageDecoder(SendMessageDecoderNegative.class);
        }else {
            super.addMessageEncoder(SendMessage.class, SendMessageEncoder.class);
            super.addMessageDecoder(ResultMessageDecoder.class);
        }
    }
}
