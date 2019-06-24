package cn.dmdream.entity;

import cn.dmdream.entity.base.Base;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName("tab_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Type extends Base {

    private String gmTypeEnName;//	varchar	类型英文缩写
    private String gmTypeChName;//	varchar	类型中文名称

}
