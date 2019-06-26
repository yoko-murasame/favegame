package cn.dmdream.game.controller;

import cn.dmdream.entity.Game;
import cn.dmdream.entity.Type;
import cn.dmdream.game.service.TypeService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("type")
public class TypeController {

    @Reference
    private TypeService typeService;

    private JsonMsg jsonMsg = null;



    @GetMapping("page/{page}/{pageSize}")
    public JsonMsg findAllByPage(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        try{
            IPage<Type> allByPage = typeService.findAllByPage(page, pageSize);
            jsonMsg = JsonMsg.makeSuccess("查询成功", allByPage);
        }catch(Exception e){
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("查询失败!" + e.getMessage(), null);
        }
        return jsonMsg;
    }
}
