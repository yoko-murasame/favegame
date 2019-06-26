package cn.dmdream.mapper;

import cn.dmdream.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> findAllUserVoByPage(@Param("start") Integer start, @Param("pageSize") Integer pageSize);
}
