package cn.dmdream.game.controller;

import cn.dmdream.entity.vo.GameVo;
import cn.dmdream.game.service.GameService;
import cn.dmdream.search.service.GameSearchService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 陈锦 on 2019/6/26.
 */
@RestController
@RequestMapping("show")
public class GameShowController {
    @Reference
    private GameService gameService;

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

    @GetMapping("gameVo/{id}")
    public ModelAndView showGameDetail2(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("game-detail");
        JsonMsg jsonMsg = gameService.findGameVoById(id);
        System.out.println(jsonMsg.getData());
        modelAndView.addObject("game", jsonMsg);
        return modelAndView;
    }
}
