package cn.dmdream.mapper;

import cn.dmdream.entity.Comment;
import cn.dmdream.entity.Game;
import cn.dmdream.entity.vo.CommentVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {
    List<CommentVo> findAllCommentVoByPage(@Param("id") Integer id, @Param("start") Integer start, @Param("pageSize") Integer pageSize);

    CommentVo findCommentVoById(Integer id);

    int save(Comment comment);
}
