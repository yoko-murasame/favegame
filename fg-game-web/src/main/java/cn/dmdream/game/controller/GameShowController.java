package cn.dmdream.game.controller;

import cn.dmdream.entity.Game;
import cn.dmdream.entity.User;
import cn.dmdream.entity.vo.GameVo;
import cn.dmdream.game.service.GameService;
import cn.dmdream.game.service.UserService;
import cn.dmdream.search.service.GameSearchService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import com.qiniu.util.Json;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 陈锦 on 2019/6/26.
 */
@RestController
@RequestMapping("show")
public class GameShowController {
    @Reference
    private GameService gameService;

    @Reference
    private UserService userService;

    @Reference
    private GameSearchService gameSearchService;

    @GetMapping("recommend/{keyword}/{page}/{pageSize}")
    public ModelAndView showGame(@PathVariable("keyword") String keyword, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("game-list");
            JsonMsg byKeywordByPage = gameSearchService.findByKeywordByPage(keyword, page, pageSize);
            modelAndView.addObject("games", byKeywordByPage);
//            System.out.println(byKeywordByPage.getData());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return modelAndView;
    }

    @GetMapping("game")
    public ModelAndView showGameDetail() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("game-detail");
        return modelAndView;
    }

    @GetMapping("game/login")
    public ModelAndView showGameDetail1() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("gameVo/{id}")
    public ModelAndView showGameDetail2(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("game-detail");
        JsonMsg game = gameService.findGameVoById(id);
        JsonMsg user = userService.findUserVoById(20);
        GameVo gameVo = (GameVo) game.getData();
        List<Game> pubAllGames = (List<Game>) gameService.findAll().getData();
        pubAllGames = pubAllGames.stream().filter((temp) -> temp.getGmPublisherId() == gameVo.getGmPublisherId()).collect(Collectors.toList());
        JsonMsg pubGames = new JsonMsg();
        pubGames.setData(pubAllGames);
//        pubAllGames.forEach(System.out::println);
        modelAndView.addObject("game", game);
        modelAndView.addObject("user", user);
        modelAndView.addObject("pubGames", pubGames);
//        System.out.println(game.getData());
//        System.out.println(user.getData());
//        System.out.println(games.getData());
        return modelAndView;
    }
}
