package cn.dmdream.cas.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class CasProperties {

    //第二篇的配置
/*    @Value("${security.cas.server.host}")
    private String casServerUrl;

    @Value("${security.cas.server.login}")
    private String casServerLoginUrl;

    @Value("${security.cas.server.logout}")
    private String casServerLogoutUrl;

    @Value("${security.cas.service.host}")
    private String appServerUrl;

    @Value("${security.cas.service.login}")
    private String appLoginUrl;

    @Value("${security.cas.service.logout}")
    private String appLogoutUrl;*/

    //第三篇的配置
    @Value("${cas.server.host.url}")
    private String casServerUrl;

    @Value("${cas.server.host.login_url}")
    private String casServerLoginUrl;

    @Value("${cas.server.host.logout_url}")
    private String casServerLogoutUrl;

    @Value("${app.server.host.url}")
    private String appServerUrl;

    @Value("${app.login.url}")
    private String appLoginUrl;

    @Value("${app.logout.url}")
    private String appLogoutUrl;

}