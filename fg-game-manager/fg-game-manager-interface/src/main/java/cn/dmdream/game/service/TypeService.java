package cn.dmdream.game.service;

import cn.dmdream.entity.Type;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface TypeService {

    IPage<Type> findAllByPage(Integer page, Integer pageSize);

    List<Type> findAll();

    Type findById(Integer id);

    boolean saveOrUpdate(Type type);

    boolean deleteById(Integer id);
}
