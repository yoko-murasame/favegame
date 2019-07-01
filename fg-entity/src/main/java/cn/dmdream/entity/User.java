package cn.dmdream.entity;

import cn.dmdream.entity.base.Base;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName("tab_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User extends Base {
    private String gmUsername;//	varchar	用户名(昵称)
    private String gmUserPhone;//	varchar	手机号
    private String gmUserQQ;//	varchar	关联QQ
    private String gmUserWechat;//	varchar	关联微信
    private Integer gmDeveloperId;  //开发者Id
}
