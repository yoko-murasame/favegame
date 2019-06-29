package cn.dmdream.game.service.impl;

import cn.dmdream.entity.Type;
import cn.dmdream.entity.User;
import cn.dmdream.entity.vo.GameVo;
import cn.dmdream.entity.vo.UserVo;
import cn.dmdream.game.service.UserService;
import cn.dmdream.mapper.UserMapper;
import cn.dmdream.utils.EmptyUtils;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 陈锦 on 2019/6/24.
 */
@Transactional
@Component
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    private JsonMsg jsonMsg;

    @Override
    public IPage<User> searchByCondition(User user, Integer page, Integer pageSize) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//        queryWrapper.like("gmUsername", user.getGmUsername());
//        queryWrapper.like("gmUserPhone", user.getGmUserPhone());
        queryWrapper.like("gmUserQQ", user.getGmUserQQ());
//        queryWrapper.like("gmUserWechat", user.getGmUserWechat());
        IPage<User> userIPage = userMapper.selectPage(new Page<User>(page, pageSize), queryWrapper);
        return userIPage;
    }

    @Override
    public IPage<User> findAllByPage(Integer page, Integer pageSize) {
        IPage<User> userIPage = userMapper.selectPage(new Page<User>(page, pageSize), null);
        return userIPage;
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    @Override
    public User findById(Integer id) {
        return null;
    }

    @Override
    public boolean saveOrUpdate(User user) {

        try {
            int i = 0;
            if (EmptyUtils.isEmpty(user.getId())) {
                i = userMapper.insert(user);
                //i = gameMapper.insert(game);//自带的方法由于设置了@TableId(type = IdType.AUTO)因此也能返回
                System.out.println("新增返回得到主键值-----");
                System.out.println(user.getId());
            } else {
                i = userMapper.updateById(user);
            }
            return i > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean deleteById(Integer id) {
        return userMapper.deleteById(id) == 1;
    }

    @Override
    public JsonMsg findUserVoById(Integer id) {
        try {
            UserVo userVoById = userMapper.findUserVoById(id);
            System.out.println("uservo:" + userVoById);
            jsonMsg = JsonMsg.makeSuccess("成功", userVoById);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }
}
