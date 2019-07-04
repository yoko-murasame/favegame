package cn.dmdream.game.service.impl;

import cn.dmdream.entity.Game;
import cn.dmdream.entity.vo.GameVo;
import cn.dmdream.game.service.GameService;
import cn.dmdream.mapper.GameMapper;
import cn.dmdream.utils.EmptyUtils;
import cn.dmdream.utils.JsonMsg;
import cn.dmdream.utils.PageModel;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional//事务
@Component
@Service(interfaceClass = GameService.class)
public class GameServiceImpl implements GameService {

    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private JmsTemplate jmsTemplate;

    private JsonMsg jsonMsg = null;



    @Override
    public JsonMsg findAll() {
        try {
            List<Game> list = gameMapper.selectList(null);
            jsonMsg = JsonMsg.makeSuccess("查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("查询失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }

    @Override
    public JsonMsg findById(Integer id) {
        try {
            Game game = gameMapper.selectById(id);
            jsonMsg = JsonMsg.makeSuccess("成功", game);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }

    @Override
    public JsonMsg saveOrUpdate(Game game) {
        try {
            int i = 0;
            if (EmptyUtils.isEmpty(game.getId())) {
                i = gameMapper.save(game);
                //i = gameMapper.insert(game);//自带的方法由于设置了@TableId(type = IdType.AUTO)因此也能返回
                System.out.println(game.getId());
            } else {
                i = gameMapper.updateById(game);
            }
            boolean isok = i > 0 ? true : false;
            if (isok) {
                //发送消息给ActiveMQ服务器,更新solr
                jmsTemplate.convertAndSend("fg-game-search-update", game.getId());
                jsonMsg = JsonMsg.makeSuccess("保存成功", null);
            } else {
                throw new Exception("保存失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败!" + e.getMessage(), null);
        }
        return jsonMsg;
    }

    @Override
    public JsonMsg deleteById(Integer id) {
        try {
            int i = gameMapper.deleteById(id);
            boolean isok = i > 0 ? true : false;
            if (isok) {
                //发送消息给ActiveMQ服务器,更新solr
                jmsTemplate.convertAndSend("fg-game-search-update", id);
                jsonMsg = JsonMsg.makeSuccess("成功", null);
            } else {
                throw new Exception("数据库保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }

    @Override
    public JsonMsg findAllGameVoByPage(Integer page, Integer pageSize, Game game, String sortField) {
        try {
            List<GameVo> list = gameMapper.findAllGameVoByPage((page - 1) * pageSize, pageSize, game, sortField);
            //查询总记录数
            QueryWrapper<Game> queryWrapper = new QueryWrapper<Game>().eq("isValid", game.getIsValid());
            if (!EmptyUtils.isEmpty(game.getGmTypeId()) && game.getGmTypeId() > -1) {
                queryWrapper = queryWrapper.eq("gmTypeId", game.getGmTypeId());
            }
            if (!EmptyUtils.isEmpty(game.getGmName())) {
                queryWrapper = queryWrapper.like("gmName", game.getGmName());
            }
            Integer count = gameMapper.selectCount(queryWrapper);
            PageModel<GameVo> pageModel1 = new PageModel<>();
            PageModel.wrapPageModel(page, pageSize, count, sortField, list, pageModel1);
            jsonMsg = JsonMsg.makeSuccess("查询成功", pageModel1);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("查询失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }

    @Override
    public JsonMsg findGameVoById(Integer id) {
        try {
            GameVo gameVo = gameMapper.findGameVoById(id);
            jsonMsg = JsonMsg.makeSuccess("成功", gameVo);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }

    @Override
    public JsonMsg checkPass(Integer id, Integer status) {
        try {
            int i = gameMapper.updateIsValid(id, status);
            if (i > 0) {
                //发送消息给ActiveMQ服务器,更新solr
                jmsTemplate.convertAndSend("fg-game-search-update", id);
                jsonMsg = JsonMsg.makeSuccess("更新成功！", null);
            } else {
                throw new Exception("更新失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail(e.getMessage(), null);
        }
        return jsonMsg;
    }

    @Override
    public JsonMsg updateAllToSolr() {

        try {
                //发送消息给ActiveMQ服务器,更新solr
                jmsTemplate.convertAndSend("updateAllToSolr","update");
                jsonMsg = JsonMsg.makeSuccess("成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }

}
