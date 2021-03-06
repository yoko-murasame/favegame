package cn.dmdream.game.service;

import cn.dmdream.entity.User;
import cn.dmdream.entity.vo.UserVo;
import cn.dmdream.utils.JsonMsg;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface UserService {

    IPage<User> findAllByPage(Integer page, Integer pageSize);

    IPage<User> searchByCondition(User user, Integer page, Integer pageSize);

    List<User> findAll();

    User findById(Integer id);

    UserVo findUserVoByPhone(String phone);

    boolean saveOrUpdate(User user);

    boolean deleteById(Integer id);

    JsonMsg findUserVoById(Integer id);

    JsonMsg findAllUserVoByPage(Integer page, Integer pageSize);

}
