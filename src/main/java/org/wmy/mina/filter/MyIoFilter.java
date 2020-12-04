package org.wmy.mina.filter;

import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.WriteRequest;

/**
 * 对于 IoHandler 中的同名方法
 * 无论是哪个方法，要注意必须在实现时调用参数nextFilter 的同名方法，
 * 否则，过滤器链的执行将被中断，IoHandler 中的同名方法一样也不会被执行。
 */
public class MyIoFilter implements IoFilter {

    public void destroy() throws Exception {
        System.out.println("---------stroy");
    }

    public void exceptionCaught(NextFilter nextFilter, IoSession session, Throwable cause) throws Exception {
        System.out.println("---------exceptionCaught");
        nextFilter.exceptionCaught(session, cause);
    }

    public void filterClose(NextFilter nextFilter, IoSession session) throws Exception {
        System.out.println("---------filterClose");
        nextFilter.filterClose(session);
    }


    public void filterWrite(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
        System.out.println("---------filterWrite");
        nextFilter.filterWrite(session, writeRequest);
    }

    /**
     * init()在首次添加到链中的时候被调用，但你必须将这个IoFilter 用
     * ReferenceCountingFilter 包装起来，否则init()方法永远不会被调用。
     * @throws Exception
     */
    public void init() throws Exception {
        System.out.println("---------init");
    }


    public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
        System.out.println("---------messageReceived");
        nextFilter.messageReceived(session, message);
    }


    public void messageSent(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception {
        System.out.println("---------messageSent");
        nextFilter.messageSent(session, writeRequest);
    }


    /**
     * onPostAdd()在调用添加到链中的方法后被调，
     * 如果在这个方法中有异常抛出，则过滤器会立即被移除，
     * 同时destroy()方法也会被调用（前提是使用ReferenceCountingFilter包装）。
     * @param parent
     * @param name
     * @param nextFilter
     * @throws Exception
     */
    public void onPostAdd(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception {
        System.out.println("---------onPostAdd");
    }


    public void onPostRemove(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception {
        System.out.println("---------onPostRemove");
    }


    /**
     * onPreAdd()在调用添加到链中的方法时被调用，但此时还未真正的加入到链。
     * @param parent
     * @param name
     * @param nextFilter
     * @throws Exception
     */
    public void onPreAdd(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception {
        System.out.println("---------onPreAdd");
    }


    public void onPreRemove(IoFilterChain parent, String name, NextFilter nextFilter) throws Exception {
        System.out.println("---------onPreRemove");
    }


    public void sessionClosed(NextFilter nextFilter, IoSession session) throws Exception {
        System.out.println("---------sessionClosed");
        nextFilter.sessionClosed(session);
    }


    public void sessionCreated(NextFilter nextFilter, IoSession session) throws Exception {
        System.out.println("---------sessionCreated");
        nextFilter.sessionCreated(session);
    }


    public void sessionIdle(NextFilter nextFilter, IoSession session, IdleStatus status) throws Exception {
        System.out.println("---------sessionIdle");
        nextFilter.sessionIdle(session, status);
    }


    public void sessionOpened(NextFilter nextFilter, IoSession session) throws Exception {
        System.out.println("---------sessionOpened");
        nextFilter.sessionOpened(session);
    }
}