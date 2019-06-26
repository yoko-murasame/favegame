package cn.dmdream.entity;

import cn.dmdream.entity.base.Base;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName("tab_operator")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Operator extends Base {
    private String gmOperatorName;//	varchar	运营商名称
    private String gmOperatorAddress;//	varchar	运营商地址
    private String gmOperatorWebsite;//	varchar	运营商网站
    private String gmOperatorIntro;//	varchar	运营商简介
    private String gmOperatorLinkman;//	varchar	运营商联系人姓名
    private String gmOperatorPhone;//	varchar	运营商联系人电话
    private String gmOperatorAlipay;//	varchar	运营商支付宝
    private String gmOperatorWechat;//	varchar	运营商微信
    private String gmOperatorCredit;//	varchar	运营商银行卡号(用于游戏付费前钱的支付)

}