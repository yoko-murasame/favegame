package cn.dmdream.game.utils;

import java.util.Map;

import cn.dmdream.entity.Order;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;

public class QRCodeCore {
    public static String AliPayCreate(String tradeNo, String price, String subject) {
        String privatrKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCVmwBgW6OQOToD/Su28DKRUCjkIrWP2Cjd6Npv4UZXZMltXHV9BVhPhchx3anYfrnFIcZOka+uSHd8v+juUYz4FrBvOJVYtcKoc/eQkECJSSnj5uAkRsH7f3LEL90kaktPpU+MzQx1yz3LP1fLy+HNhaygFyPoBd3/eJs0TcSJE4pGjqiabYmj3zSuNc4OOAgPZT7JgSWtbxT0enABZPAwOhyiya+TFtsRi1L2LjQ7QqSgIo5YRo055uxWc039T7TlVQt/H6o2icrvPuiBbvP6dN2utyQGi6KgsF8/En08aimSJs0/GQRp8N/P0KUTdiT02bOEpkrKxQd2nebxL1ynAgMBAAECggEAIdw4JJb5GR5qrBZz+2638z2yU5grgcgJz9fxAJt7ITKViGQBz28TQsRjzqm91FsyTpTLXmMI4ShTqu+Al9frUQdJnZKv8qkIyrmckaKkiK3/hi5uEqfVJcf9wLuYkLJiIz+mFpceN9btQIAwrdaSX8LehElYfh491aGFdzwWvHNQApoQiEFr3fXSVge0685nDez7XcO0Lu5Ybs9vMVH6wCr/4NCH32YuTB36MBRagtPHGNmgGG3kem4EhKcS+rwRd9oovCQtpdgsz5f7rWfc6Ih8ZuMjxLQt2SmibGQjXvfF8EwVVq+bMDjEsHtzQSQfregiQDXED6eikuDLXVuReQKBgQDOzmxlGI002vswBr6x72w+ajm2vW7pVyXCnUBtXcWwUqGGac+SCHFv3AJSLLcDtRKZ8KZfSF7TF7uEDkZBRYkbK87CLKOwNxbMy97adx1cxqpwSyBFK9N3tc+CRCnx0Gtts+aMdrbQS3HJUNFTF0I1r7t8AgXcPMCnLruxT8A7nQKBgQC5MU1ojnVx+uUVRubGdLjbbvE1d9WFJBsdD2FlFnEmY5kI5LH2MVS0XTVckYDPTVlyatoA8hUIdzZihe9ffpRJnFQ+M8yzHZGiEP+FiJwBVlGd4T3NwOsbK+sjq/lgReKWGstmYF2dawo/SqYhmTeP5bKAeX8ZtAJNFIPZ8vuwEwKBgQDNaiwwnppjjb9CjPCNPQt/U3PQF4k7LNzM2fVMrH/YC7t1D1Sl8Mz8FsQXa2K9w/WeAnK+4duOQVx4ZtVRsAfnfTP5mu1b8twqv7WzuezoYkyZDYYYLtf1aBmdgqdHEYYjiTXO/3vGmamEFUpWBhw1xz+qhTfc8ltJtBmqnVSL4QKBgFs4t+Ka2astZQ5YAHEdKbmdKl+mCZ0trdVgsB71MQMx6D4DOM4GeqzlhNV2LrbuaPL9QylYFZg38KgTRVgKp8yinrSNG3XXSNdF/DewlPfrpwRX4POq5aRau9a0bYBIrFY4GJzzNunFwiRvBuph6IaPJKA4OK1WiBOj2VbQY73VAoGBAJEqve6EwrTv3hKX/3m1vqfyWFU+wLjFG3FG9Z8ICy5Vr7c9NAJ7zwz9P/T8vPzD5XLQw2Jio1sqMXCJCYjD+tl0yvc7m0WU8cHJgE5Cx5m84cIGDQZjQ6Wx/H5xVUa5MrtZ6NYvik+9YD45KNqd3dsipq4QPAZjun/BB+q7fRhf";
        String alipayKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAllWVf2zk3vusaPVoIw7gudakFrF54SqIDZ5s5poyf8RIXl/Fq+I6cx4I2yqZ2ZiSgqtXvprx8Pg1B9xNrNvCJuLTOWDAVlKIVuCFMogeDtorvchnzcdSanw0k3fc8r6W+3sr8gQskse7zBcyYYsxxgY2NXGBTfRAU3xw5hpEpfudF8p+2o+B3rCZMHoLJKSf0ppQv/vSPwkx3/Tc45vgGv757CWxHFkPYy0QhiIgnUeWH5Pc+dEnN4vnxWVqSeeQBtOujZHU1Rlyn0p5/Xfw7zhEAx6UKaJysfcSChaFzX4bARrx0wYgXOXSXClKqYA1Xp8fyyYYa5ztfHDouk7NTQIDAQAB";
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                "2016101100662573", privatrKey, "json", "utf-8", alipayKey, "RSA2"); // 获得初始化的AlipayClient
        // 创建API对应的request类
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        /*成功后，直接跳转需要界面*/
        String return_url = "http://qbehy9.natappfree.cc/order/return_url";
        /*触发后台的控制器*/
        String notify_url = "http://qbehy9.natappfree.cc/order/notify_url";
        request.setReturnUrl(return_url);
        request.setNotifyUrl(notify_url);
        request.setBizContent("{" + "    \"out_trade_no\":\"" + tradeNo + "\"," + "    \"total_amount\":\"" + price
                + "\"," + "    \"subject\":\"" + subject + "\"" + "    }");
        // 通过alipayClient调用API，获得对应的response类
        AlipayTradePrecreateResponse response1 = null;
        try {
            response1 = alipayClient.execute(request);

        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // System.out.print(response1.getBody());
        String resString = response1.getBody();
        System.out.println(resString);
        return resString;
        // 根据response中的结果继续业务逻辑处理
        // Gson gson = new Gson();
        // Map map = gson.fromJson(response1.getBody(), Map.class);
        // System.out.println(map.get("alipay_trade_precreate_response"));
        // String sq = (String) map.get("qr_code");
        // return sq;
    }
}