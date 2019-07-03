package cn.dmdream.mapper;

import cn.dmdream.entity.Collection;
import cn.dmdream.entity.vo.CollectionVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectionMapper extends BaseMapper<Collection> {
    //根据用户的id查询收藏的所有游戏
    List<CollectionVo> findUserAllCommentVoByPage(@Param("id") Integer id, @Param("start") Integer start, @Param("pageSize") Integer pageSize);

    CollectionVo isUserCollectGame(@Param("userId") Integer userId,@Param("gameId") Integer gameId);

    boolean deleteCollectionByGameIdAndUserId(@Param("userId") Integer userId,@Param("gameId") Integer gameId);
}
