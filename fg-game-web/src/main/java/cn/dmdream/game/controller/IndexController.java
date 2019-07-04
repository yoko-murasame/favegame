package cn.dmdream.game.controller;

import cn.dmdream.entity.Game;
import cn.dmdream.entity.User;
import cn.dmdream.game.service.GameService;
import cn.dmdream.game.service.TypeService;
import cn.dmdream.game.service.UserService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 陈锦 on 2019/7/3.
 */

@RestController
@RequestMapping("user")
public class IndexController {

    @Reference
    private UserService userService;

    @Reference
    private GameService gameService;

    @Reference
    private TypeService typeService;

    @GetMapping("index")
    public ModelAndView showInfoEdit() {
        ModelAndView modelAndView = new ModelAndView();
        JsonMsg user = userService.findUserVoById(20);
        //推荐四个玩家
        JsonMsg recommendUsers = userService.findAllUserVoByPage(1, 4);
        //推荐八个游戏
        JsonMsg recommendGames = gameService.findAllGameVoByPage(1, 8, new Game(), null);
        JsonMsg types = typeService.findAllType();
        modelAndView.setViewName("index");
        modelAndView.addObject("user", user);
        modelAndView.addObject("types", types);
        modelAndView.addObject("recommendUsers", recommendUsers);
        modelAndView.addObject("recommendGames", recommendGames);
        return modelAndView;
    }

    @GetMapping("index/test")
    public JsonMsg test() {
        JsonMsg allUserVoByPage = userService.findAllUserVoByPage(1, 4);
        return allUserVoByPage;
    }

    @GetMapping("index/test1")
    public JsonMsg test1() {
        JsonMsg games = gameService.findAllGameVoByPage(1, 8, new Game(), null);
        return games;
    }
}
