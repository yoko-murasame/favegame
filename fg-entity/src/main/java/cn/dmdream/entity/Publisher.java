package cn.dmdream.entity;

import cn.dmdream.entity.base.Base;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName("tab_publisher")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Publisher extends Base {
    private String gmPublisherName;//	varchar	发行商名称
    private String gmPublisherAddress;//	varchar	发行商地址
    private String gmPublisherWebsite;//	varchar	发行商网站
    private String gmPublisherIntro;//	varchar	发行商简介
    private String gmPublisherLinkman;//	varchar	发行商联系人姓名
    private String gmPublisherPhone;//	varchar	发行商联系人电话
    private String gmPublisherAlipay;//	varchar	发行商支付宝
    private String gmPublisherWechat;//	varchar	发行商微信
    private String gmPublisherCredit;//	varchar	发行商银行卡号(用于游戏付费前钱的支付)

}
