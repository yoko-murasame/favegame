package cn.dmdream.game.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 由于SpringBoot不支持使用xml配置,所以我们把配置写成了一个配置类
 * 配置类需要在类的上方使用@Configuration注解,说明自己是一个配置类
 * SpringBoot的其他配置统一放在application.properties中
 * 所有配置都必须放在比ApplicationMain方法的同包或者子包中,SpringBoot才能扫描到
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        //添加自定义的日期转换器
        registry.addConverter(new DateConvertConfig());
    }
}
