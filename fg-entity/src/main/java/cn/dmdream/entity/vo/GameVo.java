package cn.dmdream.entity.vo;

import lombok.*;
import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GameVo implements Serializable {

    @Field("id")
    @Getter @Setter
    private Integer id;//主键
    @Field("gmName")
    @Getter @Setter
    private String gmName;//	varchar	游戏名称,必填
    @Field("gmPublisherId")
    @Getter @Setter
    private Integer gmPublisherId;//	int	发行商外键,必填
    @Field("gmPublisherName")
    @Getter @Setter
    private String gmPublisherName;//	发行商名称
    @Field("gmTypeId")
    @Getter @Setter
    private Integer gmTypeId;//	int	游戏类型外键,必填
    @Field("gmTypeName")
    @Getter @Setter
    private String gmTypeName;//游戏类型名称
    @Field("gmIcon")
    @Getter @Setter
    private String gmIcon;//	varchar	游戏icon
    @Field("gmMark")
    @Getter @Setter
    private Double gmMark;//	double	游戏评分
    @Field("gmOperatorId")
    @Getter @Setter
    private Integer gmOperatorId;//	int	游戏的运营商Id
    @Field("gmOperatorName")
    @Getter @Setter
    private String gmOperatorName;//运营商名称
    @Field("gmFree")
    @Getter @Setter
    private Integer gmFree;//	int	游戏付费类型
    @Field("gmTag")
    @Getter @Setter
    private String gmTag;//     游戏标签str1,str2,str3.... 类似这样的String类型
    @Field("gmPubdate")
    @Getter @Setter
    private Date gmPubdate;// 发行日期，由发行商填写

    @Field("countCollector")
    @Getter @Setter
    private Integer countCollector;//收藏总人数
    @Field("countAttentrion")
    @Getter @Setter
    private Integer countAttentrion;//关注总人数


    @Getter @Setter
    private BigDecimal gmPrice;//	decimal	付费金额
    @Getter @Setter
    private String gmVersion;//	varchar	游戏版本号,厂商指定,必填
    @Getter @Setter
    private Integer gmPlatformIsAndroid;//	int	是否有安卓平台
    @Getter @Setter
    private String gmAndroidUrl;//	varchar	安卓版本的下载地址
    @Getter @Setter
    private Integer gmPlatformIsIOS;//	int	是否有IOS平台
    @Getter @Setter
    private String gmIOSUrl;//	varchar	IOS版本的地址
    @Getter @Setter
    private String gmRunenv;//	varchar	游戏运行配置

    @Getter @Setter
    private String gmDetail;//	varchar	游戏详情

    //需要自定义Setter
    @Getter
    private List<String> gmIntroMedia;//	varchar	游戏介绍视频和图片
    public void setGmIntroMedia(String gmIntroMedia) {
        try{
            String[] urlArray = gmIntroMedia.split(",");
            this.gmIntroMedia= Arrays.asList(urlArray);
        }catch(Exception e){
            e.printStackTrace();
            this.gmIntroMedia = null;
        }
    }



    @Getter @Setter
    private Date createTime;
    @Getter @Setter
    private Date destroyTime;//销毁时间
    @Getter @Setter
    private Integer version;
    @Getter @Setter
    private Integer isValid;

}


