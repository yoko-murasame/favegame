package cn.dmdream.entity;

import cn.dmdream.entity.base.Base;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName("tab_record")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Record extends Base {
    private Integer gmPurchaserId;//	int	购买者id
    private Integer gmGameId;//	int	购买的游戏的id
    private Integer gmOrderId;//	int	生成的消费订单的id
}
