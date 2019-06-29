package cn.dmdream.game.service;

import cn.dmdream.utils.JsonMsg;

/**
 * Created by 陈锦 on 2019/6/29.
 */
public interface ReplyService {
    //查找某个评论下所有回复
    JsonMsg findAllByPage(Integer id, Integer page, Integer pageSize);

    JsonMsg findReplyVoById(Integer id);
}
