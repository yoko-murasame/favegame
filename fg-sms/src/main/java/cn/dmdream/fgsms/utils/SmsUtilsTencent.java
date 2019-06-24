package cn.dmdream.fgsms.utils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;

import java.io.IOException;

public class SmsUtilsTencent {

    // 短信应用 SDK AppID
    private static final int appid = 1400219385; // SDK AppID 以1400开头

    // 短信应用 SDK AppKey
    private static final String appkey = "5b2f297674e121377211c309eacdc7c8";

    // 注册短信模板 ID，需要在短信应用中申请
    private static final int templateIdRegister = 348706; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请

    // 登录短信模板
    private static final int templateIdLogin = 348705; // NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请

    // 签名
    private static final String smsSign = "dmdreamcn"; // NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请


    /**
     * 发送注册短信
     * @param phone
     * @return 激活校验码
     */
    public static String sentRegisterSms(String phone,String code) {

        try {
            String[] params = {code,"5"};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            /**
             * 指定模板单发
             * @param nationCode 国家码，如 86 为中国
             * @param phoneNumber 不带国家码的手机号
             * @param templateId 信息内容
             * @param params 模板参数列表，如模板 {1}...{2}...{3}，那么需要带三个参数
             * @param sign 签名，如果填空，系统会使用默认签名
             * @param extend 扩展码，可填空
             * @param ext 服务端原样返回的参数，可填空
             */
            SmsSingleSenderResult result = ssender.sendWithParam("86", phone,templateIdRegister, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.println(result);//{"result":0,"errmsg":"OK","ext":"","sid":"18:bcb5cff780554909a474e0ea5f892620","fee":1}
            return code;
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 发送登录短信
     * @param phone
     * @return 激活校验码
     */
    public static String sentLoginSms(String phone,String code) {

        try {
            String[] params = {code,"5"};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phone,
                    templateIdLogin, params, smsSign, "", "");
            System.out.println(result);//{"result":0,"errmsg":"OK","ext":"","sid":"18:880610b0d2784d178fa8378c0ae3d21d","fee":1}
            return code;
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 随机生成6位校验码
     * @return
     */
    public static int genCode(){
        int code = (int)((Math.random()*9+1)*100000);
        return code;
    }

}
