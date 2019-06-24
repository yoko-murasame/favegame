package cn.dmdream.game.service.impl;

import cn.dmdream.entity.Type;
import cn.dmdream.game.service.TypeService;
import cn.dmdream.mapper.TypeMapper;
import cn.dmdream.utils.EmptyUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
@Service(interfaceClass = TypeService.class)
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public IPage<Type> findAllByPage(Integer page, Integer pageSize) {
        IPage<Type> typeIPage = typeMapper.selectPage(new Page<Type>(page, pageSize), null);
        return typeIPage;
    }

    @Override
    public List<Type> findAll() {
        return typeMapper.selectList(null);
    }

    @Override
    public Type findById(Integer id) {
        return typeMapper.selectById(id);
    }

    @Override
    public boolean saveOrUpdate(Type type) {
        int i = 0;
        if (EmptyUtils.isEmpty(type.getId())) {
            i = typeMapper.insert(type);
        } else {
            i = typeMapper.updateById(type);
        }
        return i > 0 ? true : false;
    }

    @Override
    public boolean deleteById(Integer id) {
        int i = 0;
        i = typeMapper.deleteById(id);
        return i > 0 ? true : false;
    }
}
