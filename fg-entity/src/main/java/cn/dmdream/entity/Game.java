package cn.dmdream.entity;

import cn.dmdream.entity.base.Base;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@TableName("tab_game")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Game extends Base {

    private String gmName;//	varchar	游戏名称,必填
    private String gmVersion;//	varchar	游戏版本号,厂商指定,必填
    private Integer gmPublisherId;//	int	发行商外键,必填
    private Integer gmTypeId;//	int	游戏类型外键,必填
    private String gmIcon;//	varchar	游戏icon
    private Double gmMark;//	double	游戏评分
    private Integer gmPlatformIsAndroid;//	int	是否有安卓平台
    private String gmAndroidUrl;//	varchar	安卓版本的下载地址
    private Integer gmPlatformIsIOS;//	int	是否有IOS平台
    private String gmIOSUrl;//	varchar	IOS版本的地址
    private Integer gmOperatorId;//	int	游戏的运营商Id
    private String gmRunenv;//	varchar	游戏运行配置
    private String gmDetail;//	varchar	游戏详情
    private String gmIntroMedia;//	varchar	游戏介绍视频和图片
    private Integer gmFree;//	int	游戏付费类型
    private BigDecimal gmPrice;//	decimal	付费金额
    private String gmTag;//     游戏标签str1,str2,str3.... 类似这样的String类型
    private Date gmPubdate;// 发行日期，由发行商填写

}
