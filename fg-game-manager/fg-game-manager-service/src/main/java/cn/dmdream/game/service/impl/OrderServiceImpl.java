package cn.dmdream.game.service.impl;

import cn.dmdream.entity.Order;
import cn.dmdream.entity.vo.OrderVo;
import cn.dmdream.game.service.OrderService;
import cn.dmdream.mapper.OrderMapper;
import cn.dmdream.utils.EmptyUtils;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
@Service(interfaceClass = OrderService.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public IPage<Order> findAllByPage(Integer page, Integer pageSize, Integer status) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<Order>();
        orderQueryWrapper.eq("gmOrderPerformance", status);
        IPage<Order> orderIPage = orderMapper.selectPage(new Page<Order>(page, pageSize), orderQueryWrapper);
        System.out.println("----" + orderIPage.toString());
        return orderIPage;
    }

    @Override
    public JsonMsg findUserAllOrders(Integer page, Integer pageSize, Integer userId) {
//        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<Order>();
//        orderQueryWrapper.eq("gmPurchaserId", userId);
        List<OrderVo> orders = orderMapper.findUserAllOrderByPage(userId, page - 1, pageSize);
        JsonMsg jsonMsg = JsonMsg.makeSuccess("成功", orders);
        return jsonMsg;
    }

    @Override
    public List<Order> findAll() {
        return orderMapper.selectList(null);
    }

    @Override
    public Order findById(Integer id) {
        return orderMapper.selectById(id);
    }


    @Override
    public boolean saveOrUpdate(Order order) {
        int i = 0;
        if (EmptyUtils.isEmpty(order.getId())) {
            i = orderMapper.insert(order);
        } else {
            i = orderMapper.updateById(order);
        }
        return i > 0 ? true : false;
    }

    @Override
    public boolean deleteById(Integer id) {
        int i = 0;
        i = orderMapper.deleteById(id);
        return i > 0 ? true : false;
    }

    @Override
    public boolean deletdByBatch(List<Integer> id) {
        int i = 0;
        i = orderMapper.deleteBatchIds(id);
        return i > 0 ? true : false;
    }

    @Override
    public int totalCount(Integer status) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<Order>();
        queryWrapper.eq("gmOrderPerformance", status);
        return orderMapper.selectCount(queryWrapper);
    }

    @Override
    public boolean isPurchase(Integer userId, Integer gameId) {
        QueryWrapper<Order> qr = new QueryWrapper<>();
        qr.eq("gmGameId", gameId).eq("gmPurchaserId", userId).eq("gmOrderPerformance", 1);
        List<Order> orders = orderMapper.selectList(qr);
        return orders.size()>0?true:false;
    }
}
