package cn.dmdream.entity.vo;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class GameVo implements Serializable {

    @Field("id")
    private Integer id;//主键
    @Field("gmName")
    private String gmName;//	varchar	游戏名称,必填
    @Field("gmPublisherId")
    private Integer gmPublisherId;//	int	发行商外键,必填
    @Field("gmPublisherName")
    private String gmPublisherName;//	发行商名称
    @Field("gmTypeId")
    private Integer gmTypeId;//	int	游戏类型外键,必填
    @Field("gmTypeName")
    private String gmTypeName;//游戏类型名称
    @Field("gmIcon")
    private String gmIcon;//	varchar	游戏icon
    @Field("gmMark")
    private Double gmMark;//	double	游戏评分
    @Field("gmOperatorId")
    private Integer gmOperatorId;//	int	游戏的运营商Id
    @Field("gmOperatorName")
    private String gmOperatorName;//运营商名称
    @Field("gmFree")
    private Integer gmFree;//	int	游戏付费类型
    @Field("gmTag")
    private String gmTag;//     游戏标签str1,str2,str3.... 类似这样的String类型
    @Field("gmPubdate")
    private Date gmPubdate;// 发行日期，由发行商填写

    private Integer countCollector;//收藏总人数
    private Integer countAttentrion;//关注总人数

    private String gmVersion;//	varchar	游戏版本号,厂商指定,必填
    private Integer gmPlatformIsAndroid;//	int	是否有安卓平台
    private String gmAndroidUrl;//	varchar	安卓版本的下载地址
    private Integer gmPlatformIsIOS;//	int	是否有IOS平台
    private String gmIOSUrl;//	varchar	IOS版本的地址
    private String gmRunenv;//	varchar	游戏运行配置
    private String gmDetail;//	varchar	游戏详情
    private String gmIntroMedia;//	varchar	游戏介绍视频和图片
    private BigDecimal gmPrice;//	decimal	付费金额

    private Date createTime;
    private Date destroyTime;//销毁时间
    private Integer version;
    private Integer isValid;

}


