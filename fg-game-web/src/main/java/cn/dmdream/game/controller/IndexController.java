package cn.dmdream.game.controller;

import cn.dmdream.entity.Game;
import cn.dmdream.entity.User;
import cn.dmdream.game.authority.UserAuthVo;
import cn.dmdream.game.service.GameService;
import cn.dmdream.game.service.TypeService;
import cn.dmdream.game.service.UserService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qiniu.util.Json;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 陈锦 on 2019/7/3.
 */

@RestController
public class IndexController {

    @Reference
    private UserService userService;

    @Reference
    private GameService gameService;

    @Reference
    private TypeService typeService;

    @RequestMapping("myLogin")//该请求不被过滤,将被拦截从而去登录
    public ModelAndView toIndex() {
//        return new ModelAndView("redirect:user/index");
        return new ModelAndView("redirect:/");
    }

    @RequestMapping({"","/"})
    public ModelAndView showInfoEdit(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        //推荐四个玩家
        JsonMsg recommendUsers = userService.findAllUserVoByPage(1, 4);
        //推荐八个游戏
        JsonMsg recommendGames = gameService.findAllGameVoByPage(1, 8, new Game(), null);
        JsonMsg types = typeService.findAllType();
        modelAndView.setViewName("index");
        UserAuthVo userAuthVo = null;
        try {
            //从security上下文获取登录用户
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            userAuthVo = (UserAuthVo) authentication.getPrincipal();
            HttpSession session = request.getSession();
            session.setAttribute("user", userAuthVo);
            System.out.println(session.getAttribute("user"));
        } catch (Exception e) {
            System.out.println("当前未登录");
        }
        modelAndView.addObject("types", types);
        modelAndView.addObject("recommendUsers", recommendUsers);
        modelAndView.addObject("recommendGames", recommendGames);
        return modelAndView;
    }

    @GetMapping("user/index/test")
    public JsonMsg test() {
        JsonMsg allUserVoByPage = userService.findAllUserVoByPage(1, 4);
        return allUserVoByPage;
    }

    @GetMapping("user/index/test1")
    public JsonMsg test1() {
        JsonMsg games = gameService.findAllGameVoByPage(1, 8, new Game(), null);
        return games;
    }


}
