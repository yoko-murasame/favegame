package cn.dmdream.admin.controller;

import cn.dmdream.entity.Type;
import cn.dmdream.game.service.TypeService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Reference;

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

    @RequestMapping("del")
    public ModelAndView delete(Integer id) {
        System.out.println("-----------------");
        ModelAndView mav = new ModelAndView();
        boolean isok = typeService.deleteById(id);
        mav.setViewName("redirect:toType");
        return mav;
    }

    @RequestMapping("add")
    public ModelAndView save(String en, String ch) {
        System.out.println("-----------------");
        ModelAndView mav = new ModelAndView();
        Type type = new Type(en,ch);
        System.out.println(type);
        boolean isok = typeService.saveOrUpdate(type);
        mav.setViewName("redirect:toType");
        return mav;
    }


    @RequestMapping("upd/{en}/{ch}/{id}")
    public JsonMsg updType(@PathVariable("en") String en,@PathVariable("ch") String ch,@PathVariable("id") Integer id) {
        try {
            Type type = typeService.findById(id);
            type.setGmTypeChName(ch);
            type.setGmTypeEnName(en);
            boolean isok = typeService.saveOrUpdate(type);
            if (isok) {
                jsonMsg = JsonMsg.makeSuccess("修改成功", null);
            } else {
                throw new Exception("修改失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }

    @RequestMapping("del/{id}")
    public JsonMsg delType(@PathVariable("id") Integer id){

        try {
            boolean isok = typeService.deleteById(id);
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
