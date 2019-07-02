package cn.dmdream.game.service;


import cn.dmdream.entity.Order;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface OrderService {
    IPage<Order> findAllByPage(Integer page, Integer pageSize, Integer status);

    List<Order> findAll();

    Order findById(Integer id);

    boolean saveOrUpdate(Order order);

    boolean deleteById(Integer id);

    boolean deletdByBatch(List<Integer> id);

    int totalCount(Integer status);
}
