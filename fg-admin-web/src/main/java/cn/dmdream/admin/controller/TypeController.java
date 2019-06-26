package cn.dmdream.admin.controller;

import cn.dmdream.entity.Type;
import cn.dmdream.game.service.TypeService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jdk.internal.org.objectweb.asm.Handle;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("type")
public class TypeController {

    @Reference
    private TypeService typeService;

    private JsonMsg jsonMsg = null;

    @GetMapping("toType")
    public ModelAndView toType() {
        ModelAndView mav = new ModelAndView();
        List<Type> list = typeService.findAll();
        mav.addObject("list",list);
        mav.setViewName("type");
        return mav;
    }

    @PostMapping("add")
    public JsonMsg save(Type type) {
        System.out.println("--------1---------");
        try {
            System.out.println("-------2----------");
            System.out.println(type);
            boolean isok = typeService.saveOrUpdate(type);
            if (isok) {
                jsonMsg = JsonMsg.makeSuccess("修改成功", type);
            } else {
                throw new Exception("修改失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }


    @RequestMapping("upd")
    public JsonMsg updType(Type type) {
        try {
            boolean isok = typeService.saveOrUpdate(type);
            if (isok) {
                jsonMsg = JsonMsg.makeSuccess("修改成功", type);
            } else {
                throw new Exception("修改失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }

    @PostMapping("del")
    public JsonMsg delType(Type type){
        System.out.println(type.getId());
        try {
            boolean isok = typeService.deleteById(type.getId());
            if (isok) {
                jsonMsg = JsonMsg.makeSuccess("删除成功", null);
            } else {
                throw new Exception("删除失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }





    @GetMapping("page/{page}/{pageSize}")
    public JsonMsg findAllByPage(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        try{
            IPage<Type> allByPage = typeService.findAllByPage(page, pageSize);
            jsonMsg = JsonMsg.makeSuccess("查询成功", allByPage);
        }catch(Exception e){
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("查询失败!" + e.getMessage(), null);
        }
        return jsonMsg;
    }


}
