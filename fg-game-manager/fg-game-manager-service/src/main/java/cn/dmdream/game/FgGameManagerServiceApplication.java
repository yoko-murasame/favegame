package cn.dmdream.game;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class FgGameManagerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FgGameManagerServiceApplication.class, args);
    }

}
