package org.wmy.mina.server;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.wmy.mina.factory.MathProtocolCodecFactory;
import org.wmy.mina.handler.DemuxingServerHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author wmy
 * @create 2020-12-04 16:08
 */
public class DemuxingServer {
    public static void main(String[] args) throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 5);
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MathProtocolCodecFactory(true)));
        acceptor.setHandler(new DemuxingServerHandler());
        acceptor.bind(new InetSocketAddress(9123));
    }
}
