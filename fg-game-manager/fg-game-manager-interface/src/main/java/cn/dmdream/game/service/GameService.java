package cn.dmdream.game.service;

import cn.dmdream.entity.Game;
import cn.dmdream.entity.vo.GameVo;

import java.util.List;

public interface GameService {
    List<Game> findAll();

    Game findById(Integer id);

    boolean saveOrUpdate(Game game);

    boolean deleteById(Integer id);

    List<GameVo> findAllGameVoByPage(Integer page, Integer pageSize);

    GameVo findGameVoById(Integer id);
}
