package cn.dmdream.admin.controller;

import cn.dmdream.entity.Game;
import cn.dmdream.entity.Operator;
import cn.dmdream.entity.Publisher;
import cn.dmdream.entity.Type;
import cn.dmdream.entity.vo.GameVo;
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
@RequestMapping("admin/game")
public class AdminGameController {

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

    @RequestMapping({"/",""})
    public ModelAndView toGamePage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "6") Integer pageSize, Game game, @RequestParam(defaultValue = "createTime desc") String orderField) {
        //获取分页/条件/排序查询的对象
        JsonMsg jsm = gameService.findAllGameVoByPage(page, pageSize, game, orderField);
        //获取类型数组
        List<Type> typeList = typeService.findAll();
        ModelAndView mav = new ModelAndView("game");
        mav.addObject("gamePageModelJsm", jsm);
        mav.addObject("queryGame", game);
        mav.addObject("typeList", typeList);
        return mav;
    }

    @GetMapping("one/id")
    public JsonMsg findGameVoById(Integer id) {
        return gameService.findGameVoById(id);
    }

    @GetMapping("publisher")
    public JsonMsg findAllPublisher() {
        JsonMsg jsonMsg = null;
        try{
            List<Publisher> list = publisherService.findAll();
            jsonMsg = JsonMsg.makeSuccess("成功", list);
        }catch(Exception e){
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("失败:" + e.getMessage(), null);
        }
        return jsonMsg;
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

            // 是否可以上传的图片格式
            /*boolean flag = false;
            String[] imgTypes = new String[]{"jpg","jpeg","bmp","gif","png"};
            for(String fileSuffix : imgTypes) {
                if(suffix.substring(suffix.lastIndexOf(".") + 1).equalsIgnoreCase(fileSuffix)) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                throw new Exception("图片：" + suffix + " 上传格式不对！");
            }*/

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
