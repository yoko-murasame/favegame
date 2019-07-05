package cn.dmdream.game.controller;

import cn.dmdream.entity.Collection;
import cn.dmdream.entity.Comment;
import cn.dmdream.entity.Game;
import cn.dmdream.entity.Reply;
import cn.dmdream.entity.vo.GameVo;
import cn.dmdream.game.authority.UserAuthVo;
import cn.dmdream.game.service.*;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 陈锦 on 2019/7/3.
 */

@RestController
@RequestMapping("gameVo")
public class GameDetailController {

    @Reference
    private GameService gameService;

    @Reference
    private UserService userService;

    @Reference
    private CommentService commentService;

    @Reference(timeout = 1000000)
    private CollectionService collectionService;

    @Reference
    private ReplyService replyService;

    @Reference(timeout = 1000000)
    private OrderService orderService;

    //游戏详情页
    @GetMapping("{id}")
    public ModelAndView showGameDetailInfo(HttpSession session, @PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("game-detail");
        //评论列表
        JsonMsg comments = commentService.findAllByPage(id, 1, 100);
        //当前用户
        UserAuthVo nowUser = (UserAuthVo) session.getAttribute("user");
        JsonMsg isCollect = null;
        JsonMsg isPurchase = null;
        if (nowUser != null) {
            //当前用户是否收藏当前游戏
            isCollect = JsonMsg.makeSuccess("查询成功", collectionService.isCollect(nowUser.getId(), id));
            //查询是否购买过该游戏
            isPurchase = JsonMsg.makeSuccess("查询成功", orderService.isPurchase(nowUser.getId(), id));
        } else {
            isCollect = JsonMsg.makeFail("失败,用户未登录", false);
        }
        //当前游戏
        JsonMsg game = gameService.findGameVoById(id);
        GameVo gameVo = (GameVo) game.getData();
        //查询相同发布商的游戏
        Game gg = new Game();
        gg.setGmPublisherId(gameVo.getGmPublisherId());//设置当前gameVo相同的发布商id
        JsonMsg pubGames = gameService.findAllGameVoByPage(1,10,gg,null);
        //pubAllGames = pubAllGames.stream().filter((temp) -> temp.getGmPublisherId() == gameVo.getGmPublisherId()).collect(Collectors.toList());

        modelAndView.addObject("game", game);
        modelAndView.addObject("user", nowUser);
        modelAndView.addObject("comments", comments);
        modelAndView.addObject("pubGames", pubGames);
        modelAndView.addObject("isCollect", isCollect);
        modelAndView.addObject("isPurchase", isPurchase);
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
    @RequestMapping("addCollec")
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
