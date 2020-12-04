package org.wmy.mina.server;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.util.ReferenceCountingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.wmy.mina.filter.MyIoFilter;
import org.wmy.mina.handler.TcpServerHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author wmy
 * @create 2020-12-03 11:50
 */
public class TcpServer {
    public static void main(String[] args) throws IOException {
        //获得服务端实例
        IoAcceptor acceptor = new NioSocketAcceptor();
        //配置 SessionConfig
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);
        //添加 编解码 过滤器
        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
                        LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));
        //添加 自定义 过滤器
        acceptor.getFilterChain().addLast("myIoFilter",
                new ReferenceCountingFilter(new MyIoFilter()));
        //设置handler
        acceptor.setHandler(new TcpServerHandler());
        //绑定端口
        acceptor.bind(new InetSocketAddress(9124));
    }
}
