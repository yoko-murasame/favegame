package cn.dmdream.admin.controller;

import cn.dmdream.entity.Admin;
import cn.dmdream.entity.User;
import cn.dmdream.game.service.AdminService;
import cn.dmdream.game.service.UserService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 陈锦 on 2019/6/26.
 */
@RestController
@RequestMapping("admin")
public class AdminUserController {
    @Reference(timeout = 1000000)
    private AdminService adminService;
    @Reference(timeout = 10000000)
    private UserService userService;

    private JsonMsg jsonMsg;

    @GetMapping("userManage")
    public ModelAndView userManage() {
        ModelAndView modelAndView = new ModelAndView();
        IPage<User> userIPage = userService.findAllByPage(1, 5);
        if (setModel(modelAndView, userIPage)) return null;
        return modelAndView;
    }

    @GetMapping("userManage/{currPage}")
    public ModelAndView userManageByPages(@PathVariable("currPage") Integer currPage) {
        ModelAndView modelAndView = new ModelAndView();
        IPage<User> userIPage = userService.findAllByPage(currPage, 5);
        if (setModel(modelAndView, userIPage)) return null;
        return modelAndView;
    }

    @PostMapping("userManage/add")
    public JsonMsg userManageAdd(User user) {
        boolean flag = userService.saveOrUpdate(user);
        System.out.println(user);
        JsonMsg jsonMsg = JsonMsg.makeSuccess(flag + "", user);
        return jsonMsg;
    }

    @PostMapping("userManage/edit")
    public JsonMsg userManageEdit(User user) {
        boolean flag = userService.saveOrUpdate(user);
        JsonMsg jsonMsg = JsonMsg.makeSuccess(flag + "", user);
        return jsonMsg;
    }

    @PostMapping("userManage/delete")
    public JsonMsg userManageDelete(User user) {
        boolean flag = userService.deleteById(user.getId());
        JsonMsg jsonMsg = JsonMsg.makeSuccess(flag + "", user);
        return jsonMsg;
    }

//    @PostMapping("userManage/search")
//    public ModelAndView userManageSearch(User user) {
//        ModelAndView modelAndView = new ModelAndView("table-user-list");
//        System.out.println(user);
//        IPage<User> userIPage = userService.searchByCondition(user, 1, 5);
////        if (setModel(modelAndView, userIPage)) return null;
//        modelAndView.addObject("users", userIPage);
//        return modelAndView;
//    }

    private boolean setModel(ModelAndView modelAndView, IPage<User> userIPage) {
        try {
            modelAndView.setViewName("table-user-list");
            modelAndView.addObject("users", userIPage);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }
}
