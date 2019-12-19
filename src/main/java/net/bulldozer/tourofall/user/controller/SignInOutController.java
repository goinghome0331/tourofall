package net.bulldozer.tourofall.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignInOutController {
	@RequestMapping(value="/signin", method = RequestMethod.GET)
	public String showLoginForm(@RequestParam(value="error", required=false) String error,@RequestParam(value="signout", required=false) String signout,Model model){
		if(error != null){
			System.out.println("error filled");
			error ="아이디나 비밀번호가 일치하지 않습니다.";
			model.addAttribute("errorMsg", error);
		}
		if(signout != null){
			System.out.println("logout filled");
			signout ="로그아웃이 완료되었습니다.";
			model.addAttribute("signoutMsg", signout);
		}
		
		
		return "signin";
	}
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(
				AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
		cookieClearingLogoutHandler.logout(request, response, null);
		SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		securityContextLogoutHandler.logout(request, response, null);
		return "redirect:/";
	}

}
