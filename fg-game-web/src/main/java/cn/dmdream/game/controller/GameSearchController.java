package cn.dmdream.game.controller;

import cn.dmdream.entity.Game;
import cn.dmdream.game.service.GameService;
import cn.dmdream.game.service.UserService;
import cn.dmdream.search.service.GameSearchService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 搜索结果显示页
 */

@RestController
@RequestMapping("search")
public class GameSearchController {

    @Reference
    private GameSearchService gameSearchService;

    @Reference
    private GameService gameService;

    @Reference
    private UserService userService;

//    @GetMapping("{keyword}")
//    public ModelAndView searchGame(@PathVariable("keyword") String keyword) {
//        ModelAndView modelAndView = new ModelAndView();
//        try {
//            modelAndView.setViewName("game-list");
//            JsonMsg byKeywordByPage = gameSearchService.findByKeywordByPage(keyword, 1, 100);
//            modelAndView.addObject("games", byKeywordByPage);
////            System.out.println(byKeywordByPage.getData());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return modelAndView;
//    }

    //用于搜索结果显示
    @GetMapping("{keyword}")
    public ModelAndView searchGameByPage(@PathVariable("keyword") String keyword) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("game-list");
            JsonMsg searchGames = gameSearchService.findByKeywordByPage(keyword, 1, 100);
            //推荐五个游戏
            JsonMsg recommendGames = gameService.findAllGameVoByPage(1, 5, new Game(), null);
            modelAndView.addObject("recommendGames", recommendGames);
            modelAndView.addObject("games", searchGames);
//            System.out.println(byKeywordByPage.getData());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return modelAndView;
    }

    @GetMapping("test/{keyword}")
    public JsonMsg test(@PathVariable("keyword") String keyword) {
        Game game = new Game();
        game.setGmTypeId(47);
        JsonMsg byKeywordByPage = gameSearchService.findByKeywordByPage(keyword, 1, 100);
        return byKeywordByPage;
    }

    @GetMapping("test")
    public JsonMsg test1() {
        //推荐八个游戏
        JsonMsg recommendGames = gameService.findAllGameVoByPage(1, 3, new Game(), null);
        return recommendGames;
    }

    @GetMapping("testLogin")
    public JsonMsg test2() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        JsonMsg jsonMsg = JsonMsg.makeSuccess("成功", principal);
        return jsonMsg;
    }
}


