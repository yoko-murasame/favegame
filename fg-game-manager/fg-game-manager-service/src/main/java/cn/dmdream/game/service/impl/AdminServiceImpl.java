package cn.dmdream.game.service.impl;

import cn.dmdream.entity.Admin;
import cn.dmdream.game.service.AdminService;
import cn.dmdream.mapper.AdminMapper;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
@Service(interfaceClass = AdminService.class)
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public IPage<Admin> findAllByPage(Integer page, Integer pageSize) {
        return null;
    }

    @Override
    public List<Admin> findAll() {
        return adminMapper.selectList(null);
    }

    @Override
    public Admin findById(Integer id) {
        return adminMapper.selectById(id);
    }

    @Override
    public boolean saveOrUpdate(Admin admin) {
        return false;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
