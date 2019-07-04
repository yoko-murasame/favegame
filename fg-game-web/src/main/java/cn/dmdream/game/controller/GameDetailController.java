package cn.dmdream.game.controller;

import cn.dmdream.entity.Collection;
import cn.dmdream.entity.Comment;
import cn.dmdream.entity.Game;
import cn.dmdream.entity.Reply;
import cn.dmdream.entity.vo.GameVo;
import cn.dmdream.game.service.*;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 陈锦 on 2019/7/3.
 */

@CrossOrigin("http://localhost:8093")
@RestController
@RequestMapping("gameVo")
public class GameDetailController {

    @Reference
    private GameService gameService;

    @Reference
    private UserService userService;

    @Reference
    private CommentService commentService;

    @Reference
    private CollectionService collectionService;

    @Reference
    private ReplyService replyService;

    //游戏详情页
    @GetMapping("{id}")
    public ModelAndView showGameDetailInfo(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("game-detail");
        //当前游戏
        JsonMsg game = gameService.findGameVoById(id);
        //当前用户
        JsonMsg user = userService.findUserVoById(20);
        //评论列表
        JsonMsg comments = commentService.findAllByPage(id, 1, 100);
        //当前用户是否收藏当前游戏
        JsonMsg isCollect = JsonMsg.makeSuccess("成功", collectionService.isCollect(20, id));
        GameVo gameVo = (GameVo) game.getData();
        List<Game> pubAllGames = (List<Game>) gameService.findAll().getData();
        pubAllGames = pubAllGames.stream().filter((temp) -> temp.getGmPublisherId() == gameVo.getGmPublisherId()).collect(Collectors.toList());
        JsonMsg pubGames = new JsonMsg();
        pubGames.setData(pubAllGames);
//        pubAllGames.forEach(System.out::println);
        modelAndView.addObject("game", game);
        modelAndView.addObject("user", user);
        modelAndView.addObject("comments", comments);
        modelAndView.addObject("pubGames", pubGames);
        modelAndView.addObject("isCollect", isCollect);
//        System.out.println(game.getData());
//        System.out.println(user.getData());
//        System.out.println(games.getData());
        return modelAndView;
    }

    //添加评论
    @PostMapping("{id}/addComment")
    public JsonMsg showGameDetailAddComment(Comment comment) {
        System.out.println(comment);
        JsonMsg back = null;
//        commentService.
        try {
            back = commentService.save(comment);
            JsonMsg.makeSuccess("成功", back);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.makeFail("失败", e);
        }
        return back;
    }

    //添加到用户收藏夹
    @PostMapping("addCollection")
    public JsonMsg showGameDetailAddCollection(Collection collection) {
        System.out.println(collection);
        JsonMsg back = null;
        try {
            back = collectionService.saveOrUpdate(collection);
            JsonMsg.makeSuccess("成功", back);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.makeFail("失败", e);
        }
        return back;
    }

    //从用户收藏夹删除
    @PostMapping("cancleCollect")
    public JsonMsg showGameDetailCancleCollect(Collection collection) {
        System.out.println(collection);
        JsonMsg back = null;
        try {
            back = collectionService.cancleCollect(collection.getGmCollectorId(), collection.getGmGameId());
            JsonMsg.makeSuccess("成功", back);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.makeFail("失败", e);
        }
        return back;
    }

    @PostMapping("{id}/addReply")
    public JsonMsg showGameDetailAddComment(Reply reply) {
        System.out.println(reply);
        JsonMsg back = null;
        try {
            back = replyService.save(reply);
            JsonMsg.makeSuccess("成功", back);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.makeFail("失败", e);
        }
        return back;
    }

}
