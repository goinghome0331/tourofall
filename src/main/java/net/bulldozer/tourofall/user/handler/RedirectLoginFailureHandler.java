package net.bulldozer.tourofall.user.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class RedirectLoginFailureHandler implements AuthenticationFailureHandler{

	private static Logger logger = Logger.getLogger(RedirectLoginFailureHandler.class);
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		logger.infof("login failure = ip : {} ", request.getRemoteAddr());
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		
		response.sendRedirect("signin?error");
	}

}
