package org.wmy.mina.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.wmy.mina.pojo.SmsObject;

/**
 * @author wmy
 * @create 2020-12-03 13:00
 */

@Slf4j
public class TcpServerHandler extends IoHandlerAdapter {

    /**
     * 连接的对象IoSession 被创建完毕的时候，回调这个方法
     * 对于TCP 连接来说，注意此时TCP 连接并未建立
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionCreated(IoSession session) throws Exception {
        log.info("sessionCreated ....");
        super.sessionCreated(session);
    }

    /**
     * 它总是在sessionCreated()方法之后被调用。
     * 对于TCP 来说，它是在连接被建立之后调用，你可以在这里执行一些认证操作、发送数据等
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        log.info("sessionOpened ....");
        super.sessionOpened(session);
    }

    /**
     * 对于TCP 来说，连接被关闭时，调用这个方法
     * @param session
     * @throws Exception
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        log.info("sessionClosed ....");
        super.sessionClosed(session);
    }

    /**
     * 接收到消息时调用的方法；
     * 一般情况下，message 是一个IoBuffer 类，如果你使用了协议编解码器，那么可以强制转换为你需要的类型
     * 通常我们都是会使用协议编解码器的
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        log.info("messageReceived ....");

        SmsObject msg = (SmsObject) message;

        log.info("接受的消息是：{}",msg.getMessage());
        if(msg.getMessage().equals("quit")){
            session.close(true);
            return;
        }
    }
}
