package cn.dmdream.admin.controller;

import cn.dmdream.entity.Game;
import cn.dmdream.entity.Type;
import cn.dmdream.entity.vo.GameVo;
import cn.dmdream.game.service.GameService;
import cn.dmdream.game.service.PublisherService;
import cn.dmdream.game.service.TypeService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("admin/game")
public class AdminGameController {

    @Reference
    private GameService gameService;
    @Reference
    private TypeService typeService;
    @Reference
    private PublisherService publisherService;

    @RequestMapping({"/",""})
    public ModelAndView toGamePage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "6") Integer pageSize, Game game, @RequestParam(defaultValue = "createTime desc") String orderField) {
        //获取分页/条件/排序查询的对象
        JsonMsg jsm = gameService.findAllGameVoByPage(page, pageSize, game, orderField);
        //获取类型数组
        List<Type> typeList = typeService.findAll();
        ModelAndView mav = new ModelAndView("game");
        mav.addObject("gamePageModelJsm", jsm);
        mav.addObject("queryGame", game);
        mav.addObject("typeList", typeList);
        return mav;
    }

    @GetMapping("one/id")
    public JsonMsg findGameVoById(Integer id) {
        return gameService.findGameVoById(id);
    }

}
