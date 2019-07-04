package cn.dmdream.game.authority;

import org.springframework.security.core.GrantedAuthority;

/**
 * 身份权限类
 * 需要实现接口GrantedAuthority，用于封装角色信息，以便于根据角色进行权限分配
 * 角色有:user、publisher
 */
public class FgAuthority implements GrantedAuthority {

    public static final String USER = "user";
    public static final String PUBLISHER = "publisher";

    public static FgAuthority makeUserAuthority() {
        return new FgAuthority(FgAuthority.USER);
    }

    public static FgAuthority makePublisherAuthority() {
        return new FgAuthority(FgAuthority.PUBLISHER);
    }

    /**
     * 权限CODE
     */
    private String authority;

    public FgAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
