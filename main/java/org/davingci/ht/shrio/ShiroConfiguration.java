package org.davingci.ht.shrio;

import java.util.LinkedHashMap;  
import java.util.Map;  

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;  
import org.apache.shiro.mgt.SecurityManager;  
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;  
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;  
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration; 

@Configuration
public class ShiroConfiguration {
	
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		System.out.println("ShiroConfiguration.shiroFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		
		filterChainDefinitionMap.put("/logout", "logout");
		
		filterChainDefinitionMap.put("/admin/**", "authc");
		
	       // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面  
        shiroFilterFactoryBean.setLoginUrl("/login");  
        // 登录成功后要跳转的链接  
        shiroFilterFactoryBean.setSuccessUrl("/index");  
        // 未授权界面;  
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");  
  
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);  
        return shiroFilterFactoryBean; 
		
	}
	
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		
		securityManager.setRealm(myShiroRealm());
		return securityManager;
	}

    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

	
	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}
	
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		hashedCredentialsMatcher.setHashIterations(2);
		return hashedCredentialsMatcher;
	}
}
