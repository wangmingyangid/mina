package org.wmy.mina.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.wmy.mina.pojo.ResultMessage;
import org.wmy.mina.pojo.SendMessage;

/**
 * @author wmy
 * @create 2020-12-04 16:33
 */

@Slf4j
public class DemuxingClientHandler extends IoHandlerAdapter {

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        SendMessage sm = new SendMessage();
        sm.setI(100);
        sm.setJ(99);
        sm.setSymbol('-');
        session.write(sm);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        ResultMessage rs = (ResultMessage) message;
        log.info("结果：{}",rs.getResult());
    }
}
