package cn.dmdream.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 陈锦 on 2019/7/3.
 */

@Data
public class CollectionVo implements Serializable {
    //公共字段
    private Integer id;
    private Date createTime;
    private Date destroyTime;
    private Integer version;
    private Integer isValid;


    private Integer gmCollectorId;//	int	收藏者id
    private Integer gmGameId;//	int	收藏的游戏的id
    //游戏详情
    private Double gmMark;//	double	游戏评分
    private String gmType;      //游戏类型
    private String gmName;      //游戏名称
    private String gmIcon;      //游戏图片
    private String gmTypeChName;    //游戏类型中文名
    private String gmTypeEnName;    //游戏类型英文名

    private String gmGameName;//	varchar	游戏名称(冗余字段方便显示，确实冗余了= =!)
}
