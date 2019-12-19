package net.bulldozer.tourofall.user.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import net.bulldozer.tourofall.user.validation.annotation.FieldMatch;

@FieldMatch(first="password",second="confirmPassword", message="��й�ȣ�� ����ġ�մϴ�.")
public class UserRegistrationForm {
	public static final String FILE_NAME_USERNAME = "username";
	@NotEmpty(message="ID를 입력해주세요.")
	@Size(max=45, message = "최대 45까지 입력 가능합니다.")
	private String username;
	
	@NotEmpty(message="Password를 입력해주세요.")
	@Size(min=8, message = "최소8자 이상 입력해주세요.")
	private String password;
	
	@NotEmpty(message="중복확인 Password를 입력해주세요.")
	@Size(min=8, message = "최소8자 이상 입력해주세요.")
	private String confirmPassword;
	
	@NotEmpty(message="이름을 입력해주세요.")
	@Size(max=45, message = "최대 45까지 입력 가능합니다.")
	private String firstName;
	
	@NotEmpty(message="성을 입력해주세요.")
	@Size(max=45, message = "최대 45까지 입력 가능합니다.")
	private String lastName;
	
	private boolean gender;

	private Date birth;

	private SocialService signInProvider = SocialService.ordinary;
	
	private String year;
	private String month;
	private String date;
	
	@NotEmpty(message="여행지 타입을 선택해주세요")
	private String[] userPreferences;
	
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}
	
	public void setBirth() throws Exception{
		String dateString = year+"/"+month+"/"+date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		birth = sdf.parse(dateString);
	}
	
	public SocialService getSignInProvider() {
		return signInProvider;
	}
	
	public void setSignInProvider(SocialService signInProvider) {
		this.signInProvider = signInProvider;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getMonth() {
		return month;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String[] getUserPreferences() {
		return userPreferences;
	}
	public void setUserPreferences(String[] userPreferences) {
		this.userPreferences = userPreferences;
	}
	
	public boolean isNormalRegistration(){
		return signInProvider == null;
	}
	public boolean isSocialSignIn(){
		return signInProvider != null;
	}
	
	
	@Override
	public String toString() {
		return "RegistrationUserForm [username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", gender=" + gender + ", birth=" + birth + ", signInProvider="
				+ signInProvider + "]";
	}
	
}
