package org.wmy.mina.handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.wmy.mina.pojo.SmsObject;

/**
 * @author wmy
 * @create 2020-12-03 19:25
 */
public class TCPClientHandler extends IoHandlerAdapter {
    private final String values;

    public TCPClientHandler(String values) {
        this.values = values;
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        SmsObject msg = new SmsObject();
        msg.setMessage(values);
        msg.setReceiver("11111111");
        msg.setSender("22222222");

        session.write(msg);
    }
}
