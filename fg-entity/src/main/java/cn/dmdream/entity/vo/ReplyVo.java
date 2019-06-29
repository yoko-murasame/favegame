package cn.dmdream.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 陈锦 on 2019/6/29.
 */
@Data
public class ReplyVo implements Serializable {

    private Integer replierId;      //回复人的Id
    //回复人详情
    private String gmUsername;
    private String userPic;

    private Integer toCommentId;    //被回复的评论的Id
    private Integer toReplyId;      //被回复的对象的Id（可以没有）
    private Integer favor;          //点赞数
    private Integer against;        //反对数
    private String content;         //回复的内容

    //公共字段
    private Integer id;
    private Date createTime;
    private Date destroyTime;
    private Integer version;
    private Integer isValid;
}
