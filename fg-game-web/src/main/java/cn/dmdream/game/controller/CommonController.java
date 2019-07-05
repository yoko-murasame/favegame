package cn.dmdream.game.controller;

import cn.dmdream.entity.Game;
import cn.dmdream.game.authority.UserAuthVo;
import cn.dmdream.game.service.GameService;
import cn.dmdream.game.service.UserService;
import cn.dmdream.search.service.GameSearchService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 陈锦 on 2019/7/4.
 */

@CrossOrigin
@RestController
@RequestMapping("common")
public class CommonController {

    @Reference
    private GameSearchService gameSearchService;

    @Reference
    private GameService gameService;

    @Reference
    private UserService userService;

    @GetMapping("getUser")
    public JsonMsg getUser() {
        JsonMsg user =  null;
        try {
            //从security上下文获取登录用户
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserAuthVo userAuthVo = (UserAuthVo) authentication.getPrincipal();
            user = JsonMsg.makeSuccess("成功", userService.findUserVoByPhone(userAuthVo.getGmUserPhone()));
        } catch (Exception e) {
            System.out.println("当前未登录");
            user = JsonMsg.makeFail("失败",null);
        }
        return user;
    }

    @GetMapping("search/{keyword}")
    public ModelAndView search(@PathVariable("keyword") String keyword) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("game-list");
        //推荐五个游戏
        try {
            JsonMsg recommendGames = gameService.findAllGameVoByPage(1, 5, new Game(), null);
            JsonMsg searchGames = gameSearchService.findByKeywordByPage(keyword, 1, 100);
            modelAndView.addObject("recommendGames", recommendGames);
            modelAndView.addObject("games", searchGames);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return modelAndView;
    }
}
