package cn.dmdream.game.service.impl;

import cn.dmdream.entity.Reply;
import cn.dmdream.game.service.ReplyService;
import cn.dmdream.mapper.ReplyMapper;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 陈锦 on 2019/7/1.
 */
@Transactional
@Component
@Service(interfaceClass = ReplyService.class)
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    private JsonMsg jsonMsg;

    @Override
    public JsonMsg findAllByPage(Integer id, Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public JsonMsg findReplyVoById(Integer id) {
        return null;
    }

    @Override
    public JsonMsg save(Reply reply) {
        try {
            reply.setFavor(0);
            reply.setAgainst(0);
            replyMapper.save(reply);
            System.out.println(reply.getId());
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败!" + e.getMessage(), null);
        }
        return jsonMsg;
    }
}
