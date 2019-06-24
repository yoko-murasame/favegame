package cn.dmdream.game.service;

import cn.dmdream.entity.Tag;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface TagService {

    IPage<Tag> findAllByPage(Integer page, Integer pageSize);

    List<Tag> findAll();

    Tag findById(Integer id);

    boolean saveOrUpdate(Tag tag);

    boolean deleteById(Integer id);
}
