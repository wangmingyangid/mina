package org.wmy.mina.encode;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.wmy.mina.pojo.SmsObject;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * 编码器
 *
 * 在Mina 中编写编码器可以实现ProtocolEncoder，其中有encode()、dispose()两个方法需要实现。
 * 这里的dispose()方法用于在销毁编码器时释放关联的资源，由于这个方法一般我们并不关心，
 * 所以通常我们直接继承适配器ProtocolEncoderAdapter。
 * @author wmy
 * @create 2020-12-03 20:50
 */
public class CmccSipcEncoder extends ProtocolEncoderAdapter {
    private final Charset charset;

    public CmccSipcEncoder(Charset charset) {
        this.charset = charset;
    }

    /**
     * 切记在write()方法之前，要调用IoBuffer 的flip()方法，
     * 否则缓冲区的position 的后面是没有数据可以用来输出的，
     * 你必须调用flip()方法将position 移至0，limit 移至刚才的position
     * @param ioSession
     * @param message
     * @param protocolEncoderOutput
     * @throws Exception
     */
    public void encode(IoSession ioSession, Object message, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
        CharsetEncoder encoder = charset.newEncoder();

        //message 对象转为指定的发送对象
        SmsObject msg = (SmsObject) message;
        //创建IoBuffer缓存区，并设置自动扩展
        IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
        //组装应用层协议，并put到IoBuffer
        String statusLine = "M sip:wap.fetion.com.cn SIP-C/2.0";
        String sender = msg.getSender();
        String receiver = msg.getReceiver();
        String smsContent = msg.getMessage();
        buffer.putString(statusLine+'\n',encoder);
        buffer.putString("S: " + sender + "\n",encoder);
        buffer.putString("R: " + receiver + "\n", encoder);
        buffer.putString("L: " + smsContent.getBytes(charset).length +"\n",encoder);
        buffer.putString(smsContent, encoder);
        //调用flip方法为输出做准备
        buffer.flip();
        //输出IoBuffer缓存区实列
        protocolEncoderOutput.write(buffer);
    }
}
