package org.wmy.mina.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.wmy.mina.pojo.ResultMessage;
import org.wmy.mina.pojo.SendMessage;

/**
 * @author wmy
 * @create 2020-12-04 16:22
 */

@Slf4j
public class DemuxingServerHandler extends IoHandlerAdapter {

    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        session.close(true);
    }

    /**
     * 接受到消息后就返回结果
     * @param session
     * @param message
     * @throws Exception
     */
    public void messageReceived(IoSession session, Object message) throws Exception {
        SendMessage sm = (SendMessage) message;
        log.info("The message received is [ " + sm.getI() + " " + sm.getSymbol() + " " + sm.getJ() + " ]");
        ResultMessage rm = new ResultMessage();
        rm.setResult(sm.getI() + sm.getJ());
        session.write(rm);
    }
}
