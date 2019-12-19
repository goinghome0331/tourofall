package net.bulldozer.tourofall.security.service;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

public class SocialUserAuthenticationDetailsService implements SocialUserDetailsService{
	
	private UserDetailsService userDetailsService;
	
	public SocialUserAuthenticationDetailsService(UserDetailsService userDetailsService){
		this.userDetailsService = userDetailsService;
	}
	@Override
	public SocialUserDetails loadUserByUserId(String username) throws UsernameNotFoundException, DataAccessException {
		return (SocialUserDetails)userDetailsService.loadUserByUsername(username);
	}
}
