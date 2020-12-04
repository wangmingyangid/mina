package org.wmy.mina.client;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.wmy.mina.factory.CmccSipcCodecFactory;
import org.wmy.mina.handler.TCPClientHandler;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author wmy
 * @create 2020-12-04 13:19
 */
public class TcpClient01 {
    public static void main(String[] args) {
        IoConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(30000);

        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new CmccSipcCodecFactory(Charset.forName("UTF-8"))));

        connector.setHandler(new TCPClientHandler("你好!"));
        ConnectFuture future = connector.connect(new InetSocketAddress("localhost", 9124));
        future.addListener(new IoFutureListener<ConnectFuture>() {
            public void operationComplete(ConnectFuture future) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                IoSession session = future.getSession();
                System.out.println("++++++++++++++++++++++++++++");
            }
        });
        System.out.println("*************");

    }
}
