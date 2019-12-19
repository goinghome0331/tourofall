package net.bulldozer.tourofall.config;

import java.nio.charset.Charset;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import net.bulldozer.tourofall.security.service.SpringSecuritySignInAdapter;
import net.bulldozer.tourofall.user.service.ConnectionManager;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Resource
	private Environment env;
	
	@Bean
	public RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0,new StringHttpMessageConverter(Charset.forName("UTF-8")));
		restTemplate.getMessageConverters().add(1,new MappingJackson2HttpMessageConverter());
		
		return restTemplate;
	}
	
	@Autowired
    private DataSource dataSource;
	
	@Bean
	@Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)
    public UsersConnectionRepository usersConnectionRepository() {
        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(), 
            Encryptors.noOpText());
    }
	
	@Bean
	public FacebookConnectionFactory facebookConnectionFactory(){
		FacebookConnectionFactory facebookConnectionFactory = new FacebookConnectionFactory(env.getProperty("facebook.app.id"),env.getProperty("facebook.app.secret"));
		facebookConnectionFactory.setScope("public_profile,email");
		return facebookConnectionFactory;
	}
	
	@Bean
	@Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry connectionFactoryRegistry = new ConnectionFactoryRegistry();
		connectionFactoryRegistry.addConnectionFactory(facebookConnectionFactory());
		
		return connectionFactoryRegistry;
	}
	
	
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository(){
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	 
		if (authentication == null) {
			throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
		}
		return usersConnectionRepository().createConnectionRepository(authentication.getName());
	}
	
	@Bean
	public ConnectController connectController(){
		return new ConnectController(connectionFactoryLocator(),connectionRepository());
	}
	
	@Bean
	public SignInAdapter signInAdapter(){
		return new SpringSecuritySignInAdapter();
	}
	
	@Bean
	public ProviderSignInController providerSignInController(){ 
		return new ProviderSignInController(connectionFactoryLocator(),usersConnectionRepository(), signInAdapter());
	}

	@Bean
	public ProviderSignInUtils providerSignInUtils(){
		return new ProviderSignInUtils(connectionFactoryLocator(),usersConnectionRepository());
	}
	
	@Bean
	public ConnectionManager connectionManager(){
		return new ConnectionManager(facebookConnectionFactory() ,providerSignInUtils());
	}
	
//	@Autowired
//	@Qualifier(value="authenticationInterceptor")
//	HandlerInterceptor authenticationInterceptor;
//	
//	@Autowired
//	@Qualifier(value="pathInterceptor")
//	HandlerInterceptor pathInterceptor;
//	
//	
//	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {		
//		registry.addInterceptor(pathInterceptor)
//			.addPathPatterns("/**")
//			.excludePathPatterns("/signup")
//			.excludePathPatterns("/signin");
//		
//		registry.addInterceptor(authenticationInterceptor)
//		.addPathPatterns("/**")
//		.excludePathPatterns("/signup")
//		.excludePathPatterns("/signin");
//	}
}
