package cn.dmdream.game.service.impl;

import cn.dmdream.entity.Publisher;
import cn.dmdream.game.service.PublisherService;
import cn.dmdream.mapper.PublisherMapper;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Service(interfaceClass = PublisherService.class)
@Transactional
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherMapper publisherMapper;

    @Override
    public IPage<Publisher> findAllByPage(Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public List<Publisher> findAll() {
        QueryWrapper<Publisher> publisherQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Publisher> qr1 = publisherQueryWrapper.eq("isValid", 1);
        return publisherMapper.selectList(qr1);
    }

    @Override
    public Publisher findById(Integer id) {
        return null;
    }

    @Override
    public boolean saveOrUpdate(Publisher publisher) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
