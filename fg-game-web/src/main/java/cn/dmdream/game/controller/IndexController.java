package cn.dmdream.game.controller;

import cn.dmdream.game.service.TypeService;
import cn.dmdream.game.service.UserService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 陈锦 on 2019/7/3.
 */

@RestController
@RequestMapping("user")
public class IndexController {

    @Reference
    private UserService userService;

    @Reference
    private TypeService typeService;

    @GetMapping("index")
    public ModelAndView showInfoEdit() {
        ModelAndView modelAndView = new ModelAndView();
        JsonMsg user = userService.findUserVoById(20);
        JsonMsg types = typeService.findAllType();
        modelAndView.setViewName("index");
        modelAndView.addObject("user", user);
        modelAndView.addObject("types", types);
        return modelAndView;
    }

    @GetMapping("index/test")
    public JsonMsg test() {
        JsonMsg user = userService.findUserVoById(20);
        return user;
    }

    @GetMapping("index/test1")
    public JsonMsg test1() {
        return typeService.findAllType();
    }
}
