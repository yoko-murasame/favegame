package cn.dmdream.game.service.impl;

import cn.dmdream.entity.Operator;
import cn.dmdream.game.service.OperatorService;
import cn.dmdream.mapper.OperatorMapper;
import cn.dmdream.utils.EmptyUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Allchicken on 2019/6/24.
 */
@Transactional
@Component
@Service(interfaceClass = OperatorService.class)
public class OperatorServiceImpl implements OperatorService{

    @Autowired
    private OperatorMapper operatorMapper;

    @Override
    public IPage<Operator> findAllByPage(Integer page, Integer pageSize) {
        return operatorMapper.selectPage(new Page<>(page,pageSize),null);
    }

    @Override
    public List<Operator> findAll() {
        return operatorMapper.selectList(null);
    }

    @Override
    public Operator findById(Integer id) {
        return operatorMapper.selectById(id);
    }

    @Override
    public boolean saveOrUpdate(Operator operator) {
        int i = 0;
        if (EmptyUtils.isEmpty(operator.getId())) {
            i = operatorMapper.insert(operator);
        } else {
            i = operatorMapper.updateById(operator);
        }
        return i > 0 ? true : false;
    }

    @Override
    public boolean deleteById(Integer id) {
        int i = 0;
        i = operatorMapper.deleteById(id);
        return i > 0 ? true : false;
    }
}
