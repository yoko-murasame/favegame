package cn.dmdream.entity;

import cn.dmdream.entity.base.Base;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName("tab_tag")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Tag extends Base {
    private String gmTagName;//	varchar	标签名称
    private Integer gmGameId;//	int	外键，标签所属游戏id
    private Integer gmTagOwnerId;//	int	外键，标签创建人id
}
