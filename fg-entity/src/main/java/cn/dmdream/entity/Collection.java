package cn.dmdream.entity;

import cn.dmdream.entity.base.Base;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName("tab_collection")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Collection extends Base {
    private Integer gmCollectorId;//	int	收藏者id
    private Integer gmGameId;//	int	收藏的游戏的id
    private String gmGameName;//	varchar	游戏名称(冗余字段方便显示)

}
