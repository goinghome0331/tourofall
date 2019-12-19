package net.bulldozer.tourofall.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import net.bulldozer.tourofall.security.util.SecurityUtil;
import net.bulldozer.tourofall.user.dto.User;
import net.bulldozer.tourofall.user.dto.UserRegistrationForm;
import net.bulldozer.tourofall.user.service.ConnectionManager;
import net.bulldozer.tourofall.user.service.DuplicateUsernameException;
import net.bulldozer.tourofall.user.service.UserService;
import net.bulldozer.tourofall.user.util.DateList;

@Controller
@RequestMapping("/signup")
public class SignupController {
	@Autowired
	private UserService userService;

	@Autowired
	private ConnectionManager connectionManager;
	
	protected static final String MODEL_NAME_REGISTRATION_USER = "userRegistrationForm";
	protected static final String ERROR_MESSAGE_USERNAME_EXIST = "아이디가 이미 존재합니다.";
	
	private void addDateList(Model model){
		model.addAttribute("years", DateList.getYearList());
		model.addAttribute("months", DateList.getMonthList());
		model.addAttribute("dates", DateList.getDateList());
	}
	@RequestMapping(method = RequestMethod.GET)
	public String showSignupPage(Model model, WebRequest request) {
		Connection<?> connection = connectionManager.getConnection(request);
		UserRegistrationForm userRegistrationForm = connectionManager.createRegistrationUserForm(connection);
		model.addAttribute(MODEL_NAME_REGISTRATION_USER, userRegistrationForm);
		addDateList(model);
		return "signup";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processJoin(@Valid UserRegistrationForm userRegistrationForm, BindingResult result, Model model, WebRequest request)
			throws Exception {
		if (result.hasErrors()) {
			addDateList(model);
			return "signup";
		}
		userRegistrationForm.setBirth();
		System.out.println(userRegistrationForm);
		
		User createdUser = createUser(userRegistrationForm,result);
		if(createdUser == null){
			addDateList(model);
			return "signup";
		}
		
		SecurityUtil.logInUser(createdUser);
		connectionManager.signupForConnectionRepository(createdUser.getUsername(), request);
		return "redirect:/eval/evalmore";
	}
	private User createUser(UserRegistrationForm userRegistrationForm, BindingResult result){
		User createdUser = null;
		
		try{
			createdUser = userService.registerNewUser(userRegistrationForm);
		}catch(DuplicateUsernameException ex){
			addFieldError(MODEL_NAME_REGISTRATION_USER, UserRegistrationForm.FILE_NAME_USERNAME, userRegistrationForm.getUsername(),ERROR_MESSAGE_USERNAME_EXIST,result);
		}
		return createdUser;
	}
	
	private void addFieldError(String modelName, String fieldName, String fieldValue, String errorMsg, BindingResult result){
		FieldError error = new FieldError(modelName,fieldName,fieldValue,false, new String[]{errorMsg},new Object[]{},errorMsg);
		result.addError(error);
	}
	
}
