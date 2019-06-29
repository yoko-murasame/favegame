package cn.dmdream.entity;

import cn.dmdream.entity.base.Base;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 回复实体类
 */
@TableName("tab_reply")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Reply extends Base {
    private Integer replierId;      //回复人的Id
    private Integer toCommentId;    //被回复的评论的Id（表明了这条回复是在哪一条评论下的）
    private Integer toReplyId;      //被回复的对象的Id（可以没有）
    private Integer favor;          //点赞数
    private Integer against;        //反对数
    private String content;         //回复的内容
}
