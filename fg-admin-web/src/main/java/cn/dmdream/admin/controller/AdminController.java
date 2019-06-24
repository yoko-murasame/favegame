package cn.dmdream.admin.controller;

import cn.dmdream.entity.Admin;
import cn.dmdream.game.service.AdminService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Reference(timeout = 1000000)
    private AdminService adminService;

    private JsonMsg jsonMsg;

    @GetMapping("toLogin")
    public ModelAndView toLogin() {
        return new ModelAndView("login");
    }

    @GetMapping({"index", "", "/"})
    public ModelAndView toIndex() {
        return new ModelAndView("index");
    }

    @GetMapping("one/{id}")
    public JsonMsg findById(@PathVariable("id") Integer id) {
        try {
            Admin admin = adminService.findById(id);
            jsonMsg = JsonMsg.makeSuccess("查询成功!", admin);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("查询失败!" + e.getMessage(), null);
        }
        return jsonMsg;
    }

}
