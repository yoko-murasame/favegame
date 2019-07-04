package cn.dmdream.game.controller;

import cn.dmdream.entity.Game;
import cn.dmdream.game.service.GameService;
import cn.dmdream.game.service.TypeService;
import cn.dmdream.game.service.UserService;
import cn.dmdream.search.service.GameSearchService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GameController {

    @Reference
    private UserService userService;

    @Reference
    private TypeService typeService;

    @Reference
    private GameService gameService;

    @Reference
    private GameSearchService gameSearchService;

    @GetMapping({"","/"})
    public ModelAndView toIndex() {
        ModelAndView modelAndView = new ModelAndView();
        //JsonMsg user = userService.findUserVoById(20);
        JsonMsg types = typeService.findAllType();
        modelAndView.setViewName("index");
        //modelAndView.addObject("user", user);
        modelAndView.addObject("types", types);
        return modelAndView;
    }

    @RequestMapping("add")
    public JsonMsg saveOuUpdate(Game game) {
        return gameService.saveOrUpdate(game);
    }

    @GetMapping("all")
    public JsonMsg findAll() {
        return gameService.findAll();
    }

    @GetMapping("allvo")
    public JsonMsg findAllGameVo(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize, Game game, @RequestParam(defaultValue = "createTime desc") String orderField) {
        return gameService.findAllGameVoByPage(page, pageSize, game, orderField);
    }

    @GetMapping("allvoByKeyword/{keyword}/{page}/{pageSize}")
    public JsonMsg findAllGameVoByKeyword(@PathVariable("keyword") String keyword, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        return gameSearchService.findByKeywordByPage(keyword, page, pageSize);
    }

    @GetMapping("one/{id}")
    public JsonMsg findById(@PathVariable("id") Integer id) {
        return gameService.findById(id);
    }

    @GetMapping("onevo/{id}")
    public JsonMsg findGameVoById(@PathVariable("id") Integer id) {
        return gameService.findGameVoById(id);
    }

    @RequestMapping("delete/{id}")
    public JsonMsg deleteById(@PathVariable("id") Integer id) {
        return gameService.deleteById(id);
    }
}
