package org.davingci.ht.shrio;

import javax.annotation.Resource;  

import org.apache.shiro.authc.AuthenticationException;  
import org.apache.shiro.authc.AuthenticationInfo;  
import org.apache.shiro.authc.AuthenticationToken;  
import org.apache.shiro.authc.SimpleAuthenticationInfo;  
import org.apache.shiro.authz.AuthorizationInfo;  
import org.apache.shiro.authz.SimpleAuthorizationInfo;  
import org.apache.shiro.realm.AuthorizingRealm;  
import org.apache.shiro.subject.PrincipalCollection;  
import org.apache.shiro.util.ByteSource;
import org.davingci.ht.domain.Permission;
import org.davingci.ht.domain.Role;
import org.davingci.ht.domain.User;
import org.davingci.ht.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;  
  


public class MyShiroRealm extends AuthorizingRealm{

	@Resource
	private UserServiceImpl userServiceImpl;
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
		String username = (String) token.getPrincipal();
		System.out.println(token.getCredentials());
		
		User user = userServiceImpl.findByUsername(username);
		System.out.println("------>>user = " + user);
		if(user == null) {
			return null;
		}
		
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, 
				user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSatl()),
				getName()
				);
		return authenticationInfo;
		
	}	


	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("Permission Config--->>MyShiroRealm.doGetAuthorizaitionInfo()");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		User user = (User) principals.getPrimaryPrincipal();
		
		for(Role role:user.getRoles()) {
			
			authorizationInfo.addRole(role.getRolename());
			System.out.println(role.getPermissions());
			for(Permission p:role.getPermissions()) {
				System.out.println(p);
				authorizationInfo.addStringPermission(p.getPermission());
			}
		}
		return authorizationInfo;
	}

}
