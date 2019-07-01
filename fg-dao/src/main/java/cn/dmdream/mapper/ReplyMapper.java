package cn.dmdream.mapper;

import cn.dmdream.entity.Reply;
import cn.dmdream.entity.vo.ReplyVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyMapper extends BaseMapper<Reply> {
    //此处传入的Id是Commnet的Id，用于查询某条评论下的所有回复
    List<ReplyVo> findCommentAllRePlyVoByPage(@Param("id") Integer id, @Param("start") Integer start, @Param("pageSize") Integer pageSize);

    ReplyVo findRePlyVoById(Integer id);

    int save(Reply reply);
}
