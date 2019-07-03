package cn.dmdream.fgsms.smscode;

import cn.dmdream.fgsms.utils.SmsUtilsTencent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SmsCode {

    @Autowired
    private RedisTemplate redisTemplate;

    // 监听注册短信队列
    @JmsListener(destination = "fg-sms-regist-Queue")
    public void receiveRegistMessage(String message){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map params = objectMapper.readValue(message, Map.class);
            String mobile = (String) params.get("mobile");
            String code = (String) params.get("code");
            SmsUtilsTencent.sentRegisterSms(mobile,code);
            //存入redis
            redisTemplate.opsForHash().put("registSmsSet", mobile, code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 监听登录短信队列
    @JmsListener(destination = "fg-sms-login-Queue")
    public void receiveLoginMessage(String message){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map params = objectMapper.readValue(message, Map.class);
            String mobile = (String) params.get("mobile");
            String code = (String) params.get("code");
            // TODO: 2019/7/2 这里先使用固定密码,后期演示再使用短信发送
            //SmsUtilsTencent.sentLoginSms(mobile,code);
            code = "123123";
            //存入redis
            redisTemplate.opsForHash().put("loginSmsSet", mobile, code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
