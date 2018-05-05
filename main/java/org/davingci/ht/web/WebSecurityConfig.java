package org.davingci.ht.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {
	
	public final static String SESSION_KEY = "user";
	
	@Bean
	public SecurityInterceptor getSecurityInterceptor() {
		return new SecurityInterceptor();
	}
	
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
		
		//exclude intercept
		addInterceptor.excludePathPatterns("/error");
		addInterceptor.excludePathPatterns("/login");
		addInterceptor.excludePathPatterns("/index");
		addInterceptor.excludePathPatterns("/signup");
		
		
		//intercept requests
		addInterceptor.addPathPatterns("/blog/add");
		addInterceptor.addPathPatterns("/blog/delete/**");
		addInterceptor.addPathPatterns("/blog/update/**");


		

	}
	
	private class SecurityInterceptor extends HandlerInterceptorAdapter{
		
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler)
				throws Exception {
			HttpSession session = request.getSession();
			if (session.getAttribute(SESSION_KEY) != null)
				return true;
			
			String url="/login";
			response.sendRedirect(url);
			return false;
		}
	}

}
