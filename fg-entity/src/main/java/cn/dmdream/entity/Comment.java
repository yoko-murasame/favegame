package cn.dmdream.entity;

import cn.dmdream.entity.base.Base;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName("tab_comment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Comment extends Base {
    private Integer gmCritic;//	int	评论人id
    private Integer gmGameId;//	int	被评论的游戏的id
    private Double gmRate;//	double	评价星级(1~10)
    private String gmComment;//	varchar	评价内容
    private Integer favor;      //赞同数
    private Integer against;    //反对数
}
