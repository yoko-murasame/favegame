package cn.dmdream.admin.controller;

import cn.dmdream.entity.Order;
import cn.dmdream.game.service.OrderService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
   @Reference
    private OrderService orderService;

    private JsonMsg jsonMsg;
    private final int PageSize = 5;

    @GetMapping("finshed")
    public ModelAndView findFinsherOrder() {
        IPage<Order> orderIPage = orderService.findAllByPage(1, PageSize, 1);
        System.out.println("当前页+"+orderIPage.getCurrent()+"页数:"+orderIPage.getPages()+"总记录条数"+orderIPage.getTotal()+"数据:"+orderIPage.getRecords());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order");
        modelAndView.addObject("pageInfo", orderIPage);
        modelAndView.addObject("flag", 1);
        return modelAndView;
    }
    @GetMapping("classify/{currPage}/{statue}")
    public ModelAndView findOrderByClassifition(@PathVariable("currPage") Integer currPage,@PathVariable("statue") Integer statue){
        System.out.println("currPage"+currPage+"statue"+statue);
        int totalPage;
        int totalCount= orderService.totalCount(statue);
        totalPage=totalCount/PageSize;
        if(totalCount%PageSize>0){
            totalPage+=1;
        }
        if(totalPage<currPage){
            currPage=totalPage;
        }
        IPage<Order> orderIPage = orderService.findAllByPage(currPage, PageSize,statue);
        System.out.println("当前页+"+orderIPage.getCurrent()+"页数:"+orderIPage.getPages()+"总记录条数"+orderIPage.getTotal()+"数据:"+orderIPage.getRecords());
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("order");
        modelAndView.addObject("pageInfo",orderIPage);
        modelAndView.addObject("flag", statue);
        return modelAndView;
    }
    @GetMapping("delete/{id}/{currPage}/{statue}")
    public ModelAndView deleteById(@PathVariable("id") Integer id,@PathVariable("currPage") Integer currPage,@PathVariable("statue") Integer statue){
        System.out.println("id:"+id+"currpage"+currPage+"statue"+statue);
        boolean flag=orderService.deleteById(id);
        int totalPage;
        int totalCount= orderService.totalCount(statue);
        totalPage=totalCount/PageSize;
        if(totalCount%PageSize>0){
            totalPage+=1;
        }
        if(totalPage<currPage){
            currPage=totalPage;
        }
        ModelAndView modelAndView =new ModelAndView();
        if(flag){
            IPage<Order> orderIPage = orderService.findAllByPage(currPage, PageSize,statue);
            System.out.println("当前页+"+orderIPage.getCurrent()+"页数:"+orderIPage.getPages()+"总记录条数"+orderIPage.getTotal()+"数据:"+orderIPage.getRecords());
            modelAndView.setViewName("order");
            modelAndView.addObject("pageInfo",orderIPage);
            modelAndView.addObject("flag", statue);

        }
        return modelAndView;
    }
     @PostMapping("update")
     public JsonMsg updateOrder(Order order){ ;
         try {
             boolean flag = orderService.saveOrUpdate(order);
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
    @PostMapping("add")
    public JsonMsg addOrder(Order order){ ;
        try {
            boolean flag = orderService.saveOrUpdate(order);
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
    @PostMapping("deleteBatch")
    public JsonMsg deleteBatch(@RequestBody List<Integer>id){ ;
        try {
            boolean flag = orderService.deletdByBatch(id);
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



}
