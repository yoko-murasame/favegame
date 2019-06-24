package cn.dmdream.game.service;

import cn.dmdream.entity.Operator;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface OperatorService  {
    IPage<Operator> findAllByPage(Integer page, Integer pageSize);

    List<Operator> findAll();

    Operator findById(Integer id);

    boolean saveOrUpdate(Operator operator);

    boolean deleteById(Integer id);
}
