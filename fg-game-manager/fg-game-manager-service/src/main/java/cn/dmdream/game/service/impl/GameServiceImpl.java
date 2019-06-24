package cn.dmdream.game.service.impl;

import cn.dmdream.entity.Game;
import cn.dmdream.entity.vo.GameVo;
import cn.dmdream.game.service.GameService;
import cn.dmdream.mapper.GameMapper;
import cn.dmdream.utils.EmptyUtils;
import com.alibaba.dubbo.config.annotation.Service;
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

    @Override
    public List<Game> findAll() {
        return gameMapper.selectList(null);
    }

    @Override
    public Game findById(Integer id) {
        return gameMapper.selectById(id);
    }

    @Override
    public boolean saveOrUpdate(Game game) {

        try {
            int i = 0;
            if (EmptyUtils.isEmpty(game.getId())) {
                i = gameMapper.save(game);
                //i = gameMapper.insert(game);//自带的方法由于设置了@TableId(type = IdType.AUTO)因此也能返回
                System.out.println("新增返回得到主键值-----");
                System.out.println(game.getId());
            } else {
                i = gameMapper.updateById(game);
            }
            //发送消息给ActiveMQ服务器,更新solr
            jmsTemplate.convertAndSend("fg-game-search-update", game.getId());
            return i > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            int i = gameMapper.deleteById(id);
            //发送消息给ActiveMQ服务器,更新solr
            jmsTemplate.convertAndSend("fg-game-search-update", id);
            return i > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<GameVo> findAllGameVoByPage(Integer page, Integer pageSize) {
        return gameMapper.findAllGameVoByPage((page - 1) * pageSize, pageSize);
    }

    @Override
    public GameVo findGameVoById(Integer id) {
        return gameMapper.findGameVoById(id);
    }

}
