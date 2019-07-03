package cn.dmdream.game.service.impl;

import cn.dmdream.entity.Collection;
import cn.dmdream.entity.vo.CollectionVo;
import cn.dmdream.game.service.CollectionService;
import cn.dmdream.mapper.CollectionMapper;
import cn.dmdream.utils.EmptyUtils;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 陈锦 on 2019/7/3.
 */
@Transactional
@Component
@Service(interfaceClass = CollectionService.class)
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    private JsonMsg jsonMsg;

    @Override
    public JsonMsg findAllByPage(Integer id, Integer page, Integer pageSize) {
        try {
            List<CollectionVo> collectionVos = collectionMapper.findUserAllCommentVoByPage(id, (page - 1) * pageSize, pageSize);
            jsonMsg = JsonMsg.makeSuccess("成功", collectionVos);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("查询失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }

    @Override
    public JsonMsg deleteById(Integer id) {
        int i = 0;
        i = collectionMapper.deleteById(id);
        return i > 0 ? jsonMsg.makeSuccess("成功", true) : jsonMsg.makeSuccess("失败", false);
    }

    @Override
    public JsonMsg saveOrUpdate(Collection collection) {
        int i = 0;
        if (isCollect(collection.getGmCollectorId(), collection.getGmGameId())) {
            i = collectionMapper.insert(collection);
        } else {
            i = collectionMapper.updateById(collection);
        }
        return i > 0 ? jsonMsg.makeSuccess("成功", true) : jsonMsg.makeSuccess("失败", false);
    }

    @Override
    public boolean isCollect(Integer userId, Integer gameId) {
        //true -> 未收藏；false -> 已收藏
        boolean isCollect = collectionMapper.isUserCollectGame(userId, gameId) == null ? true : false;
        return isCollect;
    }

    @Override
    public JsonMsg cancleCollect(Integer userId, Integer gameId) {
        try {
            if (isCollect(userId, gameId)) {
                jsonMsg = JsonMsg.makeFail("失败", "未知错误，用户未收藏该游戏");
            } else
                jsonMsg = JsonMsg.makeSuccess("成功", collectionMapper.deleteCollectionByGameIdAndUserId(userId, gameId));
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败", e);
            return null;
        }
        return jsonMsg;
    }
}
