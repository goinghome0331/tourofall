package net.bulldozer.tourofall.common.util;

import net.bulldozer.tourofall.question.dto.Question;
import net.bulldozer.tourofall.user.dto.User;

public class CheckSameUtil {
	public static boolean sameAsFormerUser(User nowUser, User newUser){
		return nowUser==null? newUser == null : nowUser.equals(newUser);
	}
	public static boolean sameAsFormerQuestion(Question question, Question newQuestion){
		return question==null? newQuestion == null : question.equals(newQuestion);
	}
}
