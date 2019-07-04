package cn.dmdream.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 陈锦 on 2019/6/28.
 */
@Data
public class UserVo implements Serializable {

    //User信息
    private String gmUsername;//	varchar	用户名(昵称)
    private String gmUserPhone;//	varchar	手机号
    private String gmUserQQ;//	varchar	关联QQ
    private String gmUserWechat;//	varchar	关联微信/appid
    private Integer gmDeveloperId;  //开发者Id

    //UserInfo信息
    private Integer userId;//	int
    private String userPassword;//	varchar
    private Date userBirthday;//	date
    private Integer userGender;//	int
    private String userCountry;//	varchar
    private String userIntroduce;//	varchar
    private String userRealname;//	varchar
    private String userAddress;//	varchar
    private String userIDCard;// varchar
    private String userPic;

    //公共字段
    private Integer id;

    private Date createTime;

    private Date destroyTime;

    private Integer version;

    private Integer isValid;

}
