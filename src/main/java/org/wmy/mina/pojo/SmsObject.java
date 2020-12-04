package org.wmy.mina.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 协议对象类
 * @author wmy
 * @create 2020-12-03 19:14
 */
@Data
public class SmsObject {

    private String sender;// 短信发送者
    private String receiver;// 短信接受者
    private String message;// 短信内容
}
