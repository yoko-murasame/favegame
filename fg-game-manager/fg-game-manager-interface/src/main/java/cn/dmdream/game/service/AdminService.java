package cn.dmdream.game.service;

import cn.dmdream.entity.Admin;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface AdminService {

    IPage<Admin> findAllByPage(Integer page, Integer pageSize);

    List<Admin> findAll();

    Admin findById(Integer id);

    boolean saveOrUpdate(Admin admin);

    boolean deleteById(Integer id);
}
