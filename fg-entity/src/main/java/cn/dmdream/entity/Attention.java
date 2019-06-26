package cn.dmdream.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName("tab_attention")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Attention {
    private Integer gmAttentionerId;//	int	收藏者id
    private Integer gmGameId;//	int	收藏的游戏的id
}
