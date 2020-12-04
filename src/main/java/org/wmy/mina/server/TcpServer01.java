package org.wmy.mina.server;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.util.ReferenceCountingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.wmy.mina.factory.CmccSipcCodecFactory;
import org.wmy.mina.filter.MyIoFilter;
import org.wmy.mina.handler.TcpServerHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author wmy
 * @create 2020-12-04 13:13
 */
public class TcpServer01 {
    public static void main(String[] args) throws IOException {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

        // 编写协议编解码过滤器

        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new CmccSipcCodecFactory(Charset.forName("UTF-8"))));


        // 设置自定义过滤器
        acceptor.getFilterChain().addLast("myIoFilter", new ReferenceCountingFilter(new MyIoFilter()));

        // 设置handler
        acceptor.setHandler(new TcpServerHandler());

        // 绑定端口
        acceptor.bind(new InetSocketAddress(9124));

        //System.out.println(acceptor.getSessionConfig());
    }
}
