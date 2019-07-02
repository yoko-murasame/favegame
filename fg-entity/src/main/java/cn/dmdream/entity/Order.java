package cn.dmdream.entity;

import cn.dmdream.entity.base.Base;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
@TableName("tab_order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order extends Base {

    private String gmOrderId;//	int	订单编号(不同于主键,uuid)
    private BigDecimal gmOrderPrice;//	decimal	订单金额
    private Integer gmPayment;//	支付方式 微信0/支付宝1
    private Integer gmOrderPerformance;//	订单完成情况0:未完成 1:已完成 2:已关闭
    private Integer gmGameId;//	int	购买的游戏id
    private Integer gmPurchaserId;//	int	购买的用户id

}
