package cn.dmdream.game.authority;

import cn.dmdream.entity.vo.UserVo;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

/**
 * 需要实现接口UserDetails，用于封装用户的基本信息、权限信息，和entity层的最好分开使用。
 * 可以选择继承entity层的用户实体类
 */
@Data
public class UserAuthVo extends UserVo implements UserDetails{

    //账户是否过期
    private boolean isAccountNonExpired = true;
    //账户是否被锁定
    private boolean isAccountNonLocked = true;
    //密码是否过期
    private boolean isCredentialsNonExpired = true;
    //账户是否启用
    private boolean isEnabled = true;
    //用户的权限集合
    private Set<FgAuthority> authorities = new HashSet<FgAuthority>();

    @Override
    public String getPassword() {
        return super.getUserPassword();
    }

    @Override
    public String getUsername() {
        return super.getGmUsername();
    }

}
