package cn.dmdream.cas.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecondTestController {

    //需要访问登录或退出直接访问/login和/logout，再application.yml中定义的

    @RequestMapping("/")
    public String index() {
        return "访问了主页~";
    }


    @RequestMapping("/hello")
    public String hello() {
        return "不验证哦";
    }

    @PreAuthorize("hasAuthority('TEST')")//有TEST权限的才能访问
    @RequestMapping("/security")
    public String security() {
        return "hello world security";
    }

    @PreAuthorize("hasAuthority('ADMIN')")//必须要有ADMIN权限的才能访问
    @RequestMapping("/authorize")
    public String authorize() {
        return "有权限访问";
    }

    /**
     * 这里注意的是，TEST与ADMIN只是权限编码，可以自己定义一套规则，根据实际情况即可
     */
//    @RequestMapping("logout")
//    public ModelAndView logout() {
//        String logoutUrl = new CasProperties().getCasServerLogoutUrl();
//        return new ModelAndView(logoutUrl);
//    }

}
