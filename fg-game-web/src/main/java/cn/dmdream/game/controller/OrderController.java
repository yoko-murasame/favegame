package cn.dmdream.game.controller;

import cn.dmdream.entity.Order;
import cn.dmdream.game.service.OrderService;
import cn.dmdream.game.service.TypeService;
import cn.dmdream.game.service.UserService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("order")
public class OrderController {

    @Reference
    private UserService userService;
    @Reference
    private TypeService typeService;
    @Reference
    private OrderService orderService;

    @GetMapping("add/{gmOrderPrice}/{gmGameId}/{gmPurchaserId}")
    public ModelAndView addOrder(@PathVariable("gmOrderPrice")float gmOrderPrice, @PathVariable("gmGameId")int gmGameId, @PathVariable("gmPurchaserId")int gmPurchaserId, HttpSession session){
        //将游戏id封装成返回路径
        String returnUrl = "/gameVo/" + gmGameId;
        session.setAttribute("returnUrl", returnUrl);
        //开始保存订单信息
        ModelAndView modelAndView=new ModelAndView();
        Order order=new Order();
        order.setGmOrderPrice(new BigDecimal(gmOrderPrice));
        order.setGmGameId(gmGameId);
        order.setGmPurchaserId(gmPurchaserId);
        String orderId= UUID.randomUUID().toString();
        order.setGmOrderPerformance(0);
        order.setGmPayment(1);
        order.setGmOrderId(orderId);
        //boolean flag=orderService.saveOrUpdate(order);
        boolean flag=true;
        if(flag){
            modelAndView.addObject("order",order);
            modelAndView.setViewName("payPage");
            session.setAttribute("order",order);
            return modelAndView;
        }else{
            return  null;
        }
    }


    @GetMapping("returnOrderPage")
    public ModelAndView returnOrderPage(){
        ModelAndView modelAndView=new ModelAndView();
        JsonMsg user = userService.findUserVoById(20);
        JsonMsg types = typeService.findAllType();
        modelAndView.setViewName("index");
        modelAndView.addObject("user", user);
        modelAndView.addObject("types", types);
        modelAndView.setViewName("user-order");
        return modelAndView;
    }

    @GetMapping("test")
    public ModelAndView test(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("Success.html");
        modelAndView.addObject(null);
        return modelAndView;
    }

    @GetMapping("notify_url")
    public ModelAndView test1(){
        return null;
    }
    @RequestMapping("return_url")
    public ModelAndView test2(HttpSession session){
        //获取订单信息
        Order order = (Order) session.getAttribute("order");
        //修改订单为已通过
        order.setGmOrderPerformance(1);
        //更新订单
        boolean isok = orderService.saveOrUpdate(order);
        String returnUrl = (String) session.getAttribute("returnUrl");
        ModelAndView mav=new ModelAndView();
        mav.setViewName(returnUrl);
        return mav;
    }
    @RequestMapping("toSuccess")
    public ModelAndView toSuccess(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("Success");
        return mav;
    }
}
