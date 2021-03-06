package cn.dmdream.game.service;

import cn.dmdream.entity.Publisher;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface PublisherService {

    IPage<Publisher> findAllByPage(Integer page, Integer pageSize, Integer status);

    List<Publisher> findAll();

    Publisher findById(Integer id);

    boolean saveOrUpdate(Publisher publisher);

    boolean deleteById(Integer id);

    boolean deletdByBatch(List<Integer> id);

    int totalCount(Integer status);





}
