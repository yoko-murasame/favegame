package cn.dmdream.game.controller;

import cn.dmdream.entity.Game;
import cn.dmdream.game.service.GameService;
import cn.dmdream.game.service.UserService;
import cn.dmdream.search.service.GameSearchService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
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
        JsonMsg user = userService.findUserVoById(20);
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
