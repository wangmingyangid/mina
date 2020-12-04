package org.wmy.mina.client;

import org.apache.mina.core.service.IoConnector;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.wmy.mina.factory.MathProtocolCodecFactory;
import org.wmy.mina.handler.DemuxingClientHandler;

import java.net.InetSocketAddress;

/**
 * @author wmy
 * @create 2020-12-04 16:25
 */
public class DemuxingClient{
    public static void main(String[] args) {
        IoConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(3000);
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MathProtocolCodecFactory(false)));
        connector.setHandler(new DemuxingClientHandler());
        connector.connect(new InetSocketAddress("localhost", 9123));
    }

}
