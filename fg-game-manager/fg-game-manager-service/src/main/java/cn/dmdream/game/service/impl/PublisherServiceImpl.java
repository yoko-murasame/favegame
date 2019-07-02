package cn.dmdream.game.service.impl;

import cn.dmdream.entity.Publisher;
import cn.dmdream.game.service.PublisherService;
import cn.dmdream.mapper.PublisherMapper;
import cn.dmdream.utils.EmptyUtils;
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
@Service(interfaceClass = PublisherService.class)
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherMapper publisherMapper;
    @Override
    public IPage<Publisher> findAllByPage(Integer page, Integer pageSize, Integer status) {
        QueryWrapper<Publisher> queryWrapper = new QueryWrapper<Publisher>();
        queryWrapper.eq("isValid", status);
        IPage<Publisher> publisherIPage = publisherMapper.selectPage(new Page<Publisher>(page, pageSize), queryWrapper);
        return publisherIPage;
    }

    @Override
    public List<Publisher> findAll() {
        QueryWrapper<Publisher> publisherQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Publisher> qr1 = publisherQueryWrapper.eq("isValid", 1);
        return publisherMapper.selectList(qr1);
    }

    @Override
    public Publisher findById(Integer id) {
        return publisherMapper.selectById(id);
    }



    @Override
    public boolean saveOrUpdate(Publisher publisher) {
        int i = 0;
        if (EmptyUtils.isEmpty(publisher.getId())) {
            i = publisherMapper.insert(publisher);
        } else {
            i = publisherMapper.updateById(publisher);
        }
        return i > 0 ? true : false;
    }

    @Override
    public boolean deleteById(Integer id) {
        int i = 0;
        i = publisherMapper.deleteById(id);
        return i > 0 ? true : false;
    }

    @Override
    public boolean deletdByBatch(List<Integer> id) {
        int i=0;
        i=publisherMapper.deleteBatchIds(id);
        return i > 0 ? true : false;
    }

    @Override
    public int totalCount(Integer status) {
        QueryWrapper<Publisher> queryWrapper = new QueryWrapper<Publisher>();
        queryWrapper.eq("isValid", status);
        return publisherMapper.selectCount(queryWrapper);
    }


}
