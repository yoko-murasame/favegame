package cn.dmdream.entity;

import cn.dmdream.entity.base.Base;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * Created by 陈锦 on 2019/6/28.
 */
@TableName("tab_userInfo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserInfo extends Base {
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

}
