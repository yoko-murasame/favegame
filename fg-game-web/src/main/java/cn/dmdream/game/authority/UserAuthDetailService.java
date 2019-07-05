package cn.dmdream.game.authority;

import cn.dmdream.entity.vo.UserVo;
import cn.dmdream.game.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserAuthDetailService/*
	//实现UserDetailsService接口，实现loadUserByUsername方法
	implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("当前的用户名是："+username);
		//这里我为了方便，就直接返回一个用户信息，实际当中这里修改为查询数据库或者调用服务什么的来获取用户信息
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername("admin");
		userInfo.setName("admin");
		Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();
		AuthorityInfo authorityInfo = new AuthorityInfo("TEST");
		authorities.add(authorityInfo);
		userInfo.setAuthorities(authorities);
		return userInfo;
	}*/
        //实现AuthenticationUserDetailsService，实现loadUserDetails方法
        implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

    @Reference
    private UserService userService;
    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
        System.out.println("当前的用户名是：" + token.getName());
        /*这里我为了方便，就直接返回一个用户信息，实际当中这里修改为查询数据库或者调用服务什么的来获取用户信息*/
        UserAuthVo userAuthVo = new UserAuthVo();
        try{
            //查询user对象
            String phone = token.getName();
            UserVo userVo = userService.findUserVoByPhone(phone);
            //将需要的值封装到userAuthVo
            userAuthVo.setGmUsername(userVo.getGmUsername());
            userAuthVo.setGmUserPhone(userVo.getGmUserPhone());
            userAuthVo.setId(userVo.getId());
            userAuthVo.setUserPic(userVo.getUserPic());
            //权限设置
            Set<FgAuthority> authorities = new HashSet<FgAuthority>();
            Integer gmDeveloperId = userVo.getGmDeveloperId();
            authorities.add(FgAuthority.makeUserAuthority());//赋予普通用户
            //判断是否是开发者
            if (gmDeveloperId != null) {
                authorities.add(FgAuthority.makePublisherAuthority());//赋予开发者权限
                System.out.println("当前用户有开发者权限");
            }
            userAuthVo.setAuthorities(authorities);
        }catch(Exception e){
            e.printStackTrace();
        }
        return userAuthVo;
    }

}