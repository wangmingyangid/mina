package org.wmy.mina.decoder;

import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.wmy.mina.pojo.SmsObject;
import sun.rmi.runtime.Log;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @author wmy
 * @create 2020-12-04 11:01
 */

@Slf4j
public class CmccSipcDecoder extends CumulativeProtocolDecoder {

    private final Charset charset;

    public CmccSipcDecoder(Charset charset) {
        this.charset = charset;
    }

    protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer,
                               ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        //创建IoBuffer
        IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
        //创建解码字符集
        CharsetDecoder decoder = charset.newDecoder();
        //创建对象属性变量及状态变量
        String statusLine = "", sender = "", receiver = "", length = "", sms = "";
        //line表示当前行数；matchedCount 表示当前行读到了第几个字节
        int line = 1,matchedCount = 0;
        //从内部缓存读取数据
        while (ioBuffer.hasRemaining()){
            //一个字节一个字节的读取
            byte b = ioBuffer.get();
            buffer.put(b);
            //遇到 \n 且不是最后一行
            if(b == 10 && line < 5){
                matchedCount++;
                if(line == 1){
                    //每次从IoBuffer读取数据写出时，必须调用该方法
                    buffer.flip();
                    statusLine = buffer.getString(matchedCount, decoder);
                    statusLine = statusLine.substring(0, statusLine.length() - 1);
                    matchedCount = 0;
                    buffer.clear();
                }
                if (line == 2) {
                    buffer.flip();
                    sender = buffer.getString(matchedCount, decoder);
                    sender = sender.substring(0, sender.length() - 1);
                    matchedCount = 0;
                    buffer.clear();
                }
                if (line == 3) {
                    buffer.flip();
                    receiver = buffer.getString(matchedCount, decoder);
                    receiver = receiver.substring(0, receiver.length() - 1);
                    matchedCount = 0;
                    buffer.clear();
                }
                if (line == 4) {
                    buffer.flip();
                    length = buffer.getString(matchedCount, decoder);
                    length = length.substring(0, length.length() - 1);
                    matchedCount = 0;
                    buffer.clear();
                }
                line++;
            }else if (line == 5){
                matchedCount++;
                if (matchedCount == Long.parseLong(length.split(": ")[1])) {
                    buffer.flip();
                    sms = buffer.getString(matchedCount, decoder);
                    line++;
                    break;
                }
            }else {
                matchedCount++;
            }
        }
        //拼接对象
        SmsObject smsObject = new SmsObject();
        smsObject.setSender(sender.split(": ")[1]);
        smsObject.setReceiver(receiver.split(": ")[1]);
        smsObject.setMessage(sms);

        protocolDecoderOutput.write(smsObject);

        return false;
    }


}
