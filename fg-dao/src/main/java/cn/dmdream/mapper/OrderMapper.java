package cn.dmdream.mapper;

import cn.dmdream.entity.Order;
import cn.dmdream.entity.vo.OrderVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {
    List<OrderVo> findUserAllOrderByPage(@Param("id") Integer id, @Param("start") Integer start, @Param("pageSize") Integer pageSize);
}
