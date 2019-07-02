package cn.dmdream.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by 陈锦 on 2019/7/2.
 */
@Data
public class OrderVo implements Serializable {


    private String gmOrderId;//	int	订单编号(不同于主键,uuid)
    private BigDecimal gmOrderPrice;//	decimal	订单金额
    private Integer gmPayment;//	支付方式 微信0/支付宝1
    private Integer gmOrderPerformance;//	订单完成情况0:未完成 1:已完成 2:已关闭
    private Integer gmGameId;//	int	购买的游戏id

    private String gmName;      //游戏名
    private String gmIcon;      //游戏图片
    private String gmVersion;  //游戏版本号
    private BigDecimal gmPrice;     //游戏价格

    private Integer gmPurchaserId;//	int	购买的用户id

    //公共字段
    private Integer id;
    private Date createTime;
    private Date destroyTime;
    private Integer version;
    private Integer isValid;
}
