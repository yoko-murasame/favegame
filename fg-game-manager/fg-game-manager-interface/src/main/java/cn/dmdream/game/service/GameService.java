package cn.dmdream.game.service;

import cn.dmdream.entity.Game;
import cn.dmdream.utils.JsonMsg;

public interface GameService {
    JsonMsg findAll();

    JsonMsg findById(Integer id);

    JsonMsg saveOrUpdate(Game game);

    JsonMsg deleteById(Integer id);

    JsonMsg findAllGameVoByPage(Integer page, Integer pageSize, Game game, String orderField);

    JsonMsg findGameVoById(Integer id);

    JsonMsg checkPass(Integer id,Integer status);
}
