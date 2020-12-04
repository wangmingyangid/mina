package org.wmy.mina.factory;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.wmy.mina.decoder.CmccSipcDecoder;
import org.wmy.mina.encode.CmccSipcEncoder;

import java.nio.charset.Charset;

/**
 * @author wmy
 * @create 2020-12-04 13:08
 */
public class CmccSipcCodecFactory implements ProtocolCodecFactory {

    private final CmccSipcEncoder encoder;
    private final CmccSipcDecoder decoder;

    public CmccSipcCodecFactory() {
        this(Charset.defaultCharset());
    }

    public CmccSipcCodecFactory(Charset charSet) {
        this.encoder = new CmccSipcEncoder(charSet);
        this.decoder = new CmccSipcDecoder(charSet);
    }
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return encoder;
    }

    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return decoder;
    }
}
