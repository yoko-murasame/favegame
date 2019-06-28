package cn.dmdream.admin.controller;


import cn.dmdream.entity.Publisher;
import cn.dmdream.game.service.PublisherService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("publisher")
public class PublisherController {

    @Reference
    private PublisherService publisherService;

    private JsonMsg jsonMsg;

    @GetMapping("one/{id}")
    public JsonMsg findById(@PathVariable("id") Integer id) {
        try {
            Publisher publisher=publisherService.findById(id);
            jsonMsg = JsonMsg.makeSuccess("查询成功!", publisher);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("查询失败!" + e.getMessage(), null);
        }
        return jsonMsg;
    }
    @GetMapping("all")
    public ModelAndView findAllPublisher() {
        List<Publisher> publisher=null;
        String json=null;
        try {
            publisher=publisherService.findAll();
            jsonMsg = JsonMsg.makeSuccess("查询成功!", publisher);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("查询失败!" + e.getMessage(), null);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            json = objectMapper.writeValueAsString(publisher);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("publisher");
        modelAndView.addObject("jsonMsg",publisher);
        System.out.println(publisher);
        return modelAndView;

    }
    @GetMapping("add")
    public JsonMsg addPublisher(Publisher publisher) {
        System.out.println(publisher);
        Date date=new Date();
        publisher.setCreateTime(date);
        publisher.setVersion(1);
        publisher.setIsValid(1);
       try {
           boolean flag = publisherService.saveOrUpdate(publisher);
           if (flag) {
               jsonMsg = JsonMsg.makeSuccess("添加成功!", null);
           } else {
               jsonMsg = JsonMsg.makeFail("添加失败！",null);
           }
       }catch (Exception e){
           jsonMsg=JsonMsg.makeError("服务出错",e.getMessage());
       }
        return jsonMsg;

    }
    @GetMapping("update")
        public JsonMsg updatePublisher(Publisher publisher){
        System.out.println(publisher.getId());
        try {
            boolean flag = publisherService.saveOrUpdate(publisher);
            if (flag) {
                jsonMsg = JsonMsg.makeSuccess("更新成功!", null);
            } else {
                jsonMsg = JsonMsg.makeFail("更新失败！",null);
            }
        }catch (Exception e){
            jsonMsg=JsonMsg.makeError("服务出错",e.getMessage());
        }
        return jsonMsg;

    }
    @GetMapping("delete")
    public JsonMsg deletePublisherById(Integer id){
        System.out.println(id);
        try {
            boolean flag = publisherService.deleteById(id);
            if (flag) {
                jsonMsg = JsonMsg.makeSuccess("删除成功!", null);
            } else {
                jsonMsg = JsonMsg.makeFail("删除失败！",null);
            }
        }catch (Exception e){
            jsonMsg=JsonMsg.makeError("服务出错",e.getMessage());
        }
        return jsonMsg;

    }
    @PostMapping("deleteBatch")
    public JsonMsg deletePublishByBatch(@RequestBody List<Integer> id){

        try {
            boolean flag = publisherService.deletdByBatch(id);
            if (flag) {
                jsonMsg = JsonMsg.makeSuccess("删除成功!", null);
            } else {
                jsonMsg = JsonMsg.makeFail("删除失败！",null);
            }
        }catch (Exception e){
            jsonMsg=JsonMsg.makeError("服务出错",e.getMessage());
        }
        return jsonMsg;

    }


}