package cn.dmdream.fgsms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SmsController {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 需求:测试短信网关服务: 发送消息 队列:queue
     */
    @RequestMapping("/testSendSms")
    public void sendSms() {
        // 创建map对象
        Map<String, String> mapMessage = new HashMap<String, String>();
        // 放入消息
        // 手机号
        mapMessage.put("mobile", "18989849258");
        // 签名
        //mapMessage.put("templateId", "1137883405753520128");
        //code
        mapMessage.put("code", "123123");
        String jsonMap = null;
        try {
            jsonMap = new ObjectMapper().writeValueAsString(mapMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // 给短信发送网关服务发送消息 pyg-sms
        jmsTemplate.convertAndSend("fg-sms-regist-Queue",jsonMap);
    }

    /**
     * 需求:测试短信网关服务: 发送消息 队列:queue
     */
    @RequestMapping("/sendRegistSms/{phone}")
    public void sendRegistSms(@PathVariable("phone") String phone) {
        // 创建map对象
        Map<String, String> mapMessage = new HashMap<String, String>();
        // 放入消息
        // 手机号
        mapMessage.put("mobile", phone);
        // 签名
        //mapMessage.put("templateId", "1137883405753520128");
        //code
        String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        mapMessage.put("code", code);
        String jsonMap = null;
        try {
            jsonMap = new ObjectMapper().writeValueAsString(mapMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // 给短信发送网关服务发送注册消息 pyg-sms
        jmsTemplate.convertAndSend("fg-sms-regist-Queue",jsonMap);
    }


}
