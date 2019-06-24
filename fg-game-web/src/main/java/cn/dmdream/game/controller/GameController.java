package cn.dmdream.game.controller;

import cn.dmdream.entity.Game;
import cn.dmdream.entity.vo.GameVo;
import cn.dmdream.game.service.GameService;
import cn.dmdream.search.service.GameSearchService;
import cn.dmdream.utils.JsonMsg;
import cn.dmdream.utils.PageModel;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    @Reference
    private GameService gameService;

    @Reference
    private GameSearchService gameSearchService;

    private JsonMsg jsonMsg = null;

    @RequestMapping("add/{num}")
    public JsonMsg add(@PathVariable("num") Integer num) {
        Game game = new Game();
        game.setGmName("口袋妖怪" + num);
        game.setGmVersion("3.97");
        game.setGmPublisherId(1);
        game.setGmTypeId(1);
        boolean isok = gameService.saveOrUpdate(game);
        if (isok) {
            return JsonMsg.makeSuccess("保存成功", null);
        } else {
            return JsonMsg.makeFail("保存失败", null);
        }
    }

    @GetMapping("all")
    public JsonMsg findAll() {
        try {
            List<Game> list = gameService.findAll();
            jsonMsg = JsonMsg.makeSuccess("成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }

    @GetMapping("allvo")
    public JsonMsg findAllGameVo(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            List<GameVo> list = gameService.findAllGameVoByPage(page, pageSize);
            jsonMsg = JsonMsg.makeSuccess("成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }

    @GetMapping("allvoByKeyword/{keyword}/{page}/{pageSize}")
    public JsonMsg findAllGameVoByKeyword(@PathVariable("keyword") String keyword,@PathVariable("page")Integer page,@PathVariable("pageSize") Integer pageSize) {
        try {
            PageModel<GameVo> pageModel = gameSearchService.findByKeywordByPage(keyword, page, pageSize);
            jsonMsg = JsonMsg.makeSuccess("成功", pageModel);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }

    @GetMapping("one/{id}")
    public JsonMsg findById(@PathVariable("id") Integer id) {
        try {
            Game game = gameService.findById(id);
            jsonMsg = JsonMsg.makeSuccess("成功", game);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }

    @GetMapping("onevo/{id}")
    public JsonMsg findGameVoById(@PathVariable("id") Integer id) {
        try {
            GameVo gameVo = gameService.findGameVoById(id);
            jsonMsg = JsonMsg.makeSuccess("成功", gameVo);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }

    @RequestMapping("delete/{id}")
    public JsonMsg deleteById(@PathVariable("id") Integer id) {
        try {
            boolean isok = gameService.deleteById(id);
            if (isok) {
                jsonMsg = JsonMsg.makeSuccess("成功", null);
            } else {
                throw new Exception("保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }
}
