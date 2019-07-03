package cn.dmdream.game.service;

import cn.dmdream.entity.Collection;
import cn.dmdream.utils.JsonMsg;

/**
 * Created by 陈锦 on 2019/7/3.
 */
public interface CollectionService {
    //查找某个用户下所有收藏
    JsonMsg findAllByPage(Integer id, Integer page, Integer pageSize);

    //删除某条收藏记录
    JsonMsg deleteById(Integer id);

    //保存和更新
    JsonMsg saveOrUpdate(Collection collection);

    //检测一个用户是否已经收藏一个游戏
    boolean isCollect(Integer userId, Integer gameId);

    //删除某个用户收藏某个游戏的一条记录
    JsonMsg cancleCollect(Integer userId, Integer gameId);
}
