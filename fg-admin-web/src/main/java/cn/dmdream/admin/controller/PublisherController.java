package cn.dmdream.admin.controller;


import cn.dmdream.entity.Publisher;
import cn.dmdream.game.service.PublisherService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
    private final  int PageSize=5;

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
        IPage<Publisher> publisherIPage = publisherService.findAllByPage(1, PageSize,1);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("publisher");
        modelAndView.addObject("pageInfo", publisherIPage);
        modelAndView.addObject("flag",false);
        return modelAndView;
    }
        @GetMapping("all/{currPage}")
    public ModelAndView findAllPublisher(@PathVariable("currPage") Integer currPage) {
            int totalPage;
            int totalCount= publisherService.totalCount(1);
            totalPage=totalCount/PageSize;
            if(totalCount%PageSize>0){
                totalPage+=1;
            }
            if(totalPage<currPage){
                currPage=totalPage;
            }

        IPage<Publisher> publisherIPage = publisherService.findAllByPage(currPage, PageSize,1);
        System.out.println("当前页+"+publisherIPage.getCurrent()+"页数:"+publisherIPage.getPages()+"总记录条数"+publisherIPage.getTotal()+"数据:"+publisherIPage.getRecords());
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("publisher");
        modelAndView.addObject("pageInfo",publisherIPage);
        return modelAndView;


    }
    @GetMapping("add")
    public JsonMsg addPublisher(Publisher publisher) {
        System.out.println(publisher);
        Date date=new Date();
        publisher.setCreateTime(date);
        publisher.setVersion(1);
        publisher.setIsValid(0);
       try {
           boolean flag = publisherService.saveOrUpdate(publisher);
           if (flag) {
               jsonMsg = JsonMsg.makeSuccess("添加成功!", null);
           } else {
               jsonMsg = JsonMsg.makeFail("添加失败！",null);
           }
       }catch (Exception e){
           jsonMsg= JsonMsg.makeError("服务出错",e.getMessage());
       }
        return jsonMsg;

    }
    @GetMapping("update")
        public JsonMsg updatePublisher(Publisher publisher){
        try {
            boolean flag = publisherService.saveOrUpdate(publisher);

            if (flag) {
                jsonMsg = JsonMsg.makeSuccess("更新成功!", null);
            } else {
                jsonMsg = JsonMsg.makeFail("更新失败！",null);
            }
        }catch (Exception e){
            jsonMsg= JsonMsg.makeError("服务出错",e.getMessage());
        }

        return jsonMsg;

    }
    @GetMapping("examine")
    public JsonMsg updatePublisherValid(Publisher publisher){
        publisher.setIsValid(1);
        System.out.println(publisher.getId());
        try {
            boolean flag = publisherService.saveOrUpdate(publisher);

            if (flag) {
                jsonMsg = JsonMsg.makeSuccess("更新成功!", null);
            } else {
                jsonMsg = JsonMsg.makeFail("更新失败！",null);
            }
        }catch (Exception e){
            jsonMsg= JsonMsg.makeError("服务出错",e.getMessage());
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
            jsonMsg= JsonMsg.makeError("服务出错",e.getMessage());
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
            jsonMsg= JsonMsg.makeError("服务出错",e.getMessage());
        }
        return jsonMsg;

    }
    @GetMapping("valid")
    public ModelAndView findAllPublisherByValid() {
        IPage<Publisher> publisherIPage = publisherService.findAllByPage(1, PageSize,0);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("publisher");
        modelAndView.addObject("pageInfo", publisherIPage);
        modelAndView.addObject("flag",true);
        return modelAndView;
    }
    @GetMapping("valid/{currPage}")
    public ModelAndView findValidPublisher(@PathVariable("currPage") Integer currPage) {
        System.out.println(currPage);
        int totalPage;
        int totalCount= publisherService.totalCount(0);
        totalPage=totalCount/PageSize;
        if(totalCount%PageSize>0){
            totalPage+=1;
        }
        if(totalPage<currPage){
            currPage=totalPage;
        }
        IPage<Publisher> publisherIPage = publisherService.findAllByPage(currPage, PageSize,0);
        System.out.println("当前页+"+publisherIPage.getCurrent()+"页数:"+publisherIPage.getPages()+"总记录条数"+publisherIPage.getTotal()+"数据:"+publisherIPage.getRecords());
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("publisher");
        modelAndView.addObject("flag",true);
        modelAndView.addObject("pageInfo",publisherIPage);

        return modelAndView;


    }

}