package cn.dmdream.game.controller;

import cn.dmdream.entity.Game;
import cn.dmdream.entity.Operator;
import cn.dmdream.entity.Publisher;
import cn.dmdream.entity.Type;
import cn.dmdream.game.service.GameService;
import cn.dmdream.game.service.OperatorService;
import cn.dmdream.game.service.PublisherService;
import cn.dmdream.game.service.TypeService;
import cn.dmdream.utils.JsonMsg;
import com.alibaba.dubbo.config.annotation.Reference;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("publisher")
public class GamePublisherController {

    @Value("${qiniu.accessKey}")
    private String accessKey;    //访问秘钥
    @Value("${qiniu.secretKey}")
    private String secretKey;    //授权秘钥
    @Value("${qiniu.bucket}")
    private String bucket;       //存储空间名称
    @Value("${qiniu.domain}")
    private String domain;       //外链域名

    @Reference
    private GameService gameService;
    @Reference
    private TypeService typeService;
    @Reference
    private PublisherService publisherService;
    @Reference
    private OperatorService operatorService;

    @RequestMapping("/toPubPage")
    public ModelAndView toPubPage() {
        //查询当前发布者的信息,存入域
        // TODO: 2019/6/28  测试时先使用固定的publisher，后面需要改掉id，从页面读取
        Publisher publisher = publisherService.findById(32);
        ModelAndView mav = new ModelAndView("game-publisher-pubgame.html");
        mav.addObject("currPublisher", publisher);
        //获取类型数组
        List<Type> typeList = typeService.findAll();
        mav.addObject("typeList", typeList);
        return mav;
    }

    @PostMapping("saveOrUpdate")
    public JsonMsg saveOrUpdate(Game game) {
        return gameService.saveOrUpdate(game);
    }

    @GetMapping("operator")
    public JsonMsg findAllOperator() {
        JsonMsg jsonMsg = null;
        try{
            List<Operator> list = operatorService.findAll();
            jsonMsg = JsonMsg.makeSuccess("成功", list);
        }catch(Exception e){
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }

    /**
     * 七牛云上传生成凭证
     *
     * @throws Exception
     */
    @RequestMapping("/QiniuUpToken")
    public Map<String, Object> QiniuUpToken(@RequestParam String suffix) throws Exception{
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            //验证七牛云身份是否通过
            Auth auth = Auth.create(accessKey, secretKey);
            //生成凭证
            String upToken = auth.uploadToken(bucket);
            result.put("token", upToken);
            //存入外链默认域名，用于拼接完整的资源外链路径
            result.put("domain", domain);

            //生成实际路径名
            String randomFileName ="game/" + UUID.randomUUID().toString() + suffix;
            result.put("imgUrl", randomFileName);
            result.put("success", 1);
        } catch (Exception e) {
            result.put("message", "获取凭证失败，"+e.getMessage());
            result.put("success", 0);
        } finally {
            return result;
        }
    }





}
