package cn.dmdream.game.service.impl;

import cn.dmdream.entity.vo.CommentVo;
import cn.dmdream.game.service.CommentService;
import cn.dmdream.game.service.ReplyService;
import cn.dmdream.mapper.CommentMapper;
import cn.dmdream.mapper.ReplyMapper;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 陈锦 on 2019/6/29.
 */

@Transactional
@Component
@Service(interfaceClass = CommentService.class)
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ReplyMapper replyMapper;

    private JsonMsg jsonMsg;

    @Override
    public JsonMsg findAllByPage(Integer id, Integer page, Integer pageSize) {
        try {
            List<CommentVo> commentVos = commentMapper.findAllCommentVoByPage(id, page - 1, pageSize);
            //手动设置评论的所有回复
            for (CommentVo commentVo : commentVos) {
                commentVo.setReplyVos(replyMapper.findCommentAllRePlyVoByPage(commentVo.getId(), 0, 100));
            }
            jsonMsg = JsonMsg.makeSuccess("查询成功", commentVos);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("查询失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }

    @Override
    public JsonMsg findCommentVoById(Integer id) {
        try {
            CommentVo commentVo = commentMapper.findCommentVoById(id);
//            System.out.println("commentVo:" + commentVo);
            jsonMsg = JsonMsg.makeSuccess("成功", commentVo);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }
}
