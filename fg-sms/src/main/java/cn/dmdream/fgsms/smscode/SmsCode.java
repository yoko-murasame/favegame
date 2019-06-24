package cn.dmdream.fgsms.smscode;

import cn.dmdream.fgsms.utils.SmsUtilsTencent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SmsCode {

    // 监听注册短信队列
    @JmsListener(destination = "fg-sms-regist-Queue")
    public void receiveRegistMessage(String message){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map params = objectMapper.readValue(message, Map.class);
            String mobile = (String) params.get("mobile");
            String code = (String) params.get("code");
            SmsUtilsTencent.sentRegisterSms(mobile,code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 监听注册短信队列
    @JmsListener(destination = "fg-sms-login-Queue")
    public void receiveLoginMessage(String message){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map params = objectMapper.readValue(message, Map.class);
            String mobile = (String) params.get("mobile");
            String code = (String) params.get("code");
            SmsUtilsTencent.sentLoginSms(mobile,code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
