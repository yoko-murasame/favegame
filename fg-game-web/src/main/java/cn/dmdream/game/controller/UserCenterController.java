package cn.dmdream.game.controller;

import cn.dmdream.game.service.CollectionService;
import cn.dmdream.game.service.OrderService;
import cn.dmdream.game.service.UserService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 个人中心
 */

@RestController
@RequestMapping("userCenter")
public class UserCenterController {


    @Reference
    private UserService userService;

    @Reference
    private CollectionService collectionService;

    @Reference
    private OrderService orderService;

    @GetMapping("{id}")
    public ModelAndView showUserMain(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        JsonMsg user = userService.findUserVoById(id);
        JsonMsg collections = collectionService.findAllByPage(id, 1, 100);
        modelAndView.addObject("user", user);
        modelAndView.addObject("collections", collections);
        modelAndView.setViewName("user-center");
        return modelAndView;
    }

    @GetMapping("userInfoEdit")
    public ModelAndView showInfoEdit() {
        ModelAndView modelAndView = new ModelAndView();
        JsonMsg user = userService.findUserVoById(20);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("userInfo-edit");
        return modelAndView;
    }

    @PostMapping("deleteCollection/{id}")
    public JsonMsg showGameDetailAddComment(@PathVariable("id") Integer id) {
        System.out.println(id);
        JsonMsg back = null;
        try {
            back = collectionService.deleteById(id);
            back.makeSuccess("成功", back);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.makeFail("失败", e);
        }
        return back;
    }

    @GetMapping("order/{id}")
    public ModelAndView showOrder(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        JsonMsg user = userService.findUserVoById(20);
        JsonMsg orders = orderService.findUserAllOrders(1, 100, id);
        modelAndView.addObject("user", user);
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("user-order");
        return modelAndView;
    }

}
