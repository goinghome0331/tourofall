package net.bulldozer.tourofall.security.dto;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

import net.bulldozer.tourofall.user.dto.Role;
import net.bulldozer.tourofall.user.dto.SocialService;

public class UserAuthenticationDetails extends SocialUser {
	private long id;
	 
    private String firstName;
 
    private String lastName;
 
    private Role role;
    
    private Date birth;
    
    private	boolean gender;
    
    private SocialService signInProvider;
    
	public UserAuthenticationDetails(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public static Builder getBuilder(){
		return new Builder();
	}
	public long getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public Role getRole() {
		return role;
	}
	public Date getBirth() {
		return birth;
	}
	public boolean getGender() {
		return gender;
	}
	public SocialService getSignInProvider() {
		return signInProvider;
	}

	@Override
	public String toString() {
		return "AuthenticationUserDetails [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", role="
				+ role + "]";
	}
	
	public static class Builder{
		private long id;
		private String username;
		private String firstName;
		private String lastName;
		private String password;
		private Role role;
		private Date birth;
		private boolean gender;
		private SocialService signInProvider;
		private Set<GrantedAuthority> authorities;
		
		public Builder(){
			this.authorities = new HashSet<>();
		}
		public Builder id(long id){
			this.id = id;
			return this;
		}
		
		public Builder firstName(String firstName){
			this.firstName = firstName;
			return this;
		}
		public Builder lastName(String lastName){
			this.lastName = lastName;
			return this;
		}
		public Builder password(String password){
			this.password = password;
			return this;
		}
		public Builder role(Role role){
			this.role = role;
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
			this.authorities.add(authority);
			return this;
		}
		public Builder username(String username){
			this.username = username;
			return this;
		}
		public Builder birth(Date birth){
			this.birth = birth;
			return this;
		}
		public Builder gender(boolean gender){
			this.gender = gender;
			return this;
		}
		public Builder signInProvider(SocialService signInProvider){
			this.signInProvider = signInProvider;
			return this;
		}
		
		public UserAuthenticationDetails build(){
			UserAuthenticationDetails authenticationUserDetails = new UserAuthenticationDetails(username,password,authorities);
			
			authenticationUserDetails.id = id;
			authenticationUserDetails.firstName = firstName;
			authenticationUserDetails.lastName = lastName;
			authenticationUserDetails.birth = birth;
			authenticationUserDetails.role = role;
			authenticationUserDetails.gender = gender;
			
			return authenticationUserDetails;
		}
	}
}
