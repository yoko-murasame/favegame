package cn.dmdream.entity.vo;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by 陈锦 on 2019/6/29.
 */
@Data
public class CommentVo implements Serializable {

    private Integer gmCritic;//	int	评论人id
    //评论人详情
    private String gmUsername;      //评论人用户名
    private String userPic;         //评论人头像
    private Integer gmGameId;//	int	被评论的游戏的id
    private Double gmRate;//	double	评价星级(1~10)
    private String gmComment;//	varchar	评价内容

    private List<ReplyVo> replyVos; //回复列表


    //公共字段
    private Integer id;
    private Date createTime;
    private Date destroyTime;
    private Integer version;
    private Integer isValid;
}
