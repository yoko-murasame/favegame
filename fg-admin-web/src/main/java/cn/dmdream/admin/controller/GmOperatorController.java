package cn.dmdream.admin.controller;

import cn.dmdream.entity.Operator;
import cn.dmdream.game.service.OperatorService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Allchicken on 2019/6/24.
 */

@RestController
@RequestMapping("operator")
public class GmOperatorController {

    @Reference
    private OperatorService operatorService;

    private JsonMsg jsonMsg;

    @GetMapping("findAll")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("operator-list");
        List<Operator> list = operatorService.findAll();
        modelAndView.addObject("list",list);
        return modelAndView;
    }

    @GetMapping("find/{id}")
    public JsonMsg findById(@PathVariable("id") Integer id){

        try {
            Operator operator = operatorService.findById(id);
            jsonMsg = JsonMsg.makeSuccess("查询成功！",operator);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("查询失败！"+e.getMessage(),null);
        }

        return jsonMsg;
    }

    @GetMapping("save")
    public JsonMsg save(Operator operator){
        System.out.println(operator);
        try {
            boolean isok = operatorService.saveOrUpdate(operator);
            jsonMsg = JsonMsg.makeSuccess("添加成功！",isok);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("添加失败！",null);
        }


        return jsonMsg;

    }

    @GetMapping("delete")
    public JsonMsg deleteById(Operator operator){

        Integer id = operator.getId();
        try {
            boolean isok = operatorService.deleteById(id);
            jsonMsg = JsonMsg.makeSuccess("删除成功！",isok);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("删除失败！"+e.getMessage(),null);
        }

        return jsonMsg;
    }

    @GetMapping("update")
    public JsonMsg update(Operator operator){

        Integer id = operator.getId();
        try {
            Operator oper = operatorService.findById(id);
            jsonMsg = JsonMsg.makeSuccess("成功！",oper);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败",null);
        }

        return jsonMsg;
    }

    @GetMapping("updateConf")
    public JsonMsg updateConf(Operator operator){

        try {
            boolean isok = operatorService.saveOrUpdate(operator);
            jsonMsg = JsonMsg.makeSuccess("修改成功",isok);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("修改失败",null);
        }

        return jsonMsg;
    }

}
