package cn.dmdream.game.service;

import cn.dmdream.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface UserService {

    IPage<User> findAllByPage(Integer page, Integer pageSize);

    IPage<User> searchByCondition(User user, Integer page, Integer pageSize);

    List<User> findAll();

    User findById(Integer id);

    boolean saveOrUpdate(User user);

    boolean deleteById(Integer id);

}
