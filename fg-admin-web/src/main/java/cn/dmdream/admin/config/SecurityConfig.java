package cn.dmdream.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 自定义页面参考 https://woodwhales.github.io/2019/04/12/026/
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()                                // 定义当需要用户登录时候，转到的登录页面。
                .loginPage("/admin/")                        // 设置登录页面
                .loginProcessingUrl("/admin/login")            // 自定义的登录接口,用于自定义表单的post请求路径
                .defaultSuccessUrl("/admin/index").permitAll()        // 登录成功之后，默认跳转的页面
                .and().authorizeRequests()                    // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/admin/login", "/admin/toLogin").permitAll()        // 设置所有人都可以访问登录页面
                .anyRequest().authenticated()                // 任何请求,登录后可以访问
                .and().csrf().disable();                    // 关闭csrf防护
    }

    /**
     * 加密错误错误参考 https://blog.csdn.net/canon_in_d_major/article/details/79675033
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("yoko").password(new BCryptPasswordEncoder().encode("123")).roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**", "/**/*.css", "/**/*.js");
    }
}
