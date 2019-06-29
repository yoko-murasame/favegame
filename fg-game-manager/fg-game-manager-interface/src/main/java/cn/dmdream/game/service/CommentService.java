package cn.dmdream.game.service;

import cn.dmdream.utils.JsonMsg;

/**
 * Created by 陈锦 on 2019/6/29.
 */
public interface CommentService {
    //查找某个游戏下所有评论
    JsonMsg findAllByPage(Integer id, Integer page, Integer pageSize);

    JsonMsg findCommentVoById(Integer id);
}
