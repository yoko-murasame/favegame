package cn.dmdream.mapper;

import cn.dmdream.entity.Game;
import cn.dmdream.entity.vo.GameVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GameMapper extends BaseMapper<Game> {

    List<GameVo> findAllGameVoByPage(@Param("start") Integer start, @Param("pageSize") Integer pageSize,@Param("game") Game game,@Param("orderField") String orderField);

    GameVo findGameVoById(Integer id);

    int save(Game game);

    int updateIsValid(@Param("id") Integer id, @Param("status") Integer status);
}
