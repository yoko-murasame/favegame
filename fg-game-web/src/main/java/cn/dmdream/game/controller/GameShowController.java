package cn.dmdream.game.controller;

import cn.dmdream.entity.*;
import cn.dmdream.entity.vo.GameVo;
import cn.dmdream.game.service.*;
import cn.dmdream.search.service.GameSearchService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import com.qiniu.util.Json;
import org.springframework.web.bind.annotation.*;
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

    @Reference
    private CommentService commentService;

    @Reference
    private ReplyService replyService;

    @Reference
    private OrderService orderService;

    @Reference
    private CollectionService collectionService;

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

    @GetMapping("game/login")
    public ModelAndView showGameDetailLogin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("gameVo/test")
    public JsonMsg getTest() {
        JsonMsg commentVoById = gameService.findGameVoById(43);
        return commentVoById;
    }

    @GetMapping("gameVo/test1")
    public JsonMsg getTest1() {
        JsonMsg isCollect = JsonMsg.makeSuccess("成功", collectionService.isCollect(20, 47));
        return isCollect;
    }

    @GetMapping("gameVo/test2")
    public JsonMsg getTest2() {
        JsonMsg collections = collectionService.findAllByPage(20, 1, 100);
        return collections;
    }

    @GetMapping("gameVo/test3")
    public JsonMsg getTest3() {
        JsonMsg collections = collectionService.cancleCollect(20, 47);
        return collections;
    }
}
