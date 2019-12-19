package net.bulldozer.tourofall.security.service;

import javax.inject.Inject;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import net.bulldozer.tourofall.security.util.SecurityUtil;
import net.bulldozer.tourofall.user.dto.User;
import net.bulldozer.tourofall.user.repository.UserRepository;

@Service
public class SpringSecuritySignInAdapter implements SignInAdapter {
	
	@Inject
	private UserRepository userRepository;
	
	@Override
	public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
		User user = userRepository.findByUsername(localUserId);
		SecurityUtil.logInUser(user);
		return null;
	}

}
