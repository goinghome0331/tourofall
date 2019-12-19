package net.bulldozer.tourofall.user.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import net.bulldozer.tourofall.security.dto.UserAuthenticationDetails;

@Component
public class RedirectLoginSuccessHandler implements AuthenticationSuccessHandler {

	private static Logger logger = Logger.getLogger(RedirectLoginSuccessHandler.class);
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		response.setStatus(HttpServletResponse.SC_OK);
		
		UserAuthenticationDetails au = (UserAuthenticationDetails)authentication.getPrincipal();
		logger.infof("{} user login success = ip : {}", au.getUsername(), request.getRemoteAddr());
		
		response.sendRedirect("/");
	}

}
