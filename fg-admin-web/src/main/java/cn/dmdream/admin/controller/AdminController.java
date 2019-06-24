package cn.dmdream.admin.controller;

import cn.dmdream.entity.Admin;
import cn.dmdream.game.service.AdminService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Reference
    private AdminService adminService;

    private JsonMsg jsonMsg;

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
