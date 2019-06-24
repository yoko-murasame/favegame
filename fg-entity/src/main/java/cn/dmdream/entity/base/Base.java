package cn.dmdream.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public abstract class Base implements Serializable {

    @TableId(type = IdType.AUTO)
    protected Integer id;//无意义自增主键

    @TableField("createTime")
    protected Date createTime = new Date();//创建时间，自动生成

    @TableField("destroyTime")
    protected Date destroyTime;//销毁时间

    @TableField("version")
    protected Integer version = 1;//乐观锁，初始化为1

    @TableField("isValid")
    protected Integer isValid = 1;//是否启用，初始化为1

}