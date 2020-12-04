package org.wmy.mina.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.wmy.mina.handler.TCPClientHandler;
import org.wmy.mina.handler.TcpServerHandler;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author wmy
 * @create 2020-12-03 19:21
 */
@Slf4j
public class TcpClient {
    public static void main(String[] args) {
        IoConnector connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(3000);
        //添加过滤器
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
                        LineDelimiter.WINDOWS.getValue(),
                        LineDelimiter.WINDOWS.getValue())));

        connector.setHandler(new TCPClientHandler("你好！\r\n 大家好！"));

        //该方法时异步执行的方法
        final ConnectFuture future = connector.connect(new InetSocketAddress("localhost", 9124));
        future.addListener(new IoFutureListener<IoFuture>() {
            //异步执行的结果返回时回调该方法；这样的好处是不会产生阻塞
            public void operationComplete(IoFuture ioFuture) {
                //此出睡眠是为了体现出异步执行的效果；* 号比 + 号会先打印
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                IoSession session = future.getSession();
                log.info("++++++++++++++++++++++++++++++");
            }
        });
        log.info("************");
    }
}
