package net.bulldozer.tourofall.user.service;

public class DuplicateUsernameException extends Exception {
	public DuplicateUsernameException(String username){
		super(username);
	}
}
