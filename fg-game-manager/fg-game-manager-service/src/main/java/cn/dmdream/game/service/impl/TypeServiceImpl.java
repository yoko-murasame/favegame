package cn.dmdream.game.service.impl;

import cn.dmdream.entity.Type;
import cn.dmdream.game.service.TypeService;
import cn.dmdream.mapper.TypeMapper;
import cn.dmdream.utils.EmptyUtils;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
@Service(interfaceClass = TypeService.class)
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public IPage<Type> findAllByPage(Integer page, Integer pageSize) {
        IPage<Type> typeIPage = typeMapper.selectPage(new Page<Type>(page, pageSize), null);
        return typeIPage;
    }

    @Override
    public List<Type> findAll() {
        try {
            List<Type> typeList = (List<Type>) redisTemplate.opsForValue().get("typeList");
            if (typeList == null) {
                typeList = typeMapper.selectList(new QueryWrapper<Type>().eq("isValid", 1));
                redisTemplate.opsForValue().set("typeList", typeList);
                System.out.println("已将---游戏分类---数据库内容更新到Redis缓存");
            }
            return typeList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public JsonMsg findAllType() {
        JsonMsg types = null;
        try {
            List<Type> typeList = typeMapper.selectList(null);
            types = JsonMsg.makeSuccess("成功", typeList);
        } catch (Exception e) {
            e.printStackTrace();
            types.makeFail("失败", e);
        }
        return types;
    }

    @Override
    public Type findById(Integer id) {
        return typeMapper.selectById(id);
    }

    @Override
    public boolean saveOrUpdate(Type type) {
        int i = 0;
        if (EmptyUtils.isEmpty(type.getId())) {
            i = typeMapper.insert(type);
        } else {
            i = typeMapper.updateById(type);
        }
        if (i > 0) {
            redisTemplate.delete("typeList");
            redisTemplate.opsForValue().set("typeList", typeMapper.selectList(new QueryWrapper<Type>().eq("isValid", 1)));
        }
        return i > 0 ? true : false;
    }

    @Override
    public boolean deleteById(Integer id) {
        int i = 0;
        i = typeMapper.deleteById(id);
        if (i > 0) {
            redisTemplate.delete("typeList");
            redisTemplate.opsForValue().set("typeList", typeMapper.selectList(new QueryWrapper<Type>().eq("isValid", 1)));
        }
        return i > 0 ? true : false;
    }

}
