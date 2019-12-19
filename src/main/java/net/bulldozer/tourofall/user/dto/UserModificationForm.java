package net.bulldozer.tourofall.user.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import net.bulldozer.tourofall.user.validation.annotation.FieldMatch;

@FieldMatch(first="password",second="confirmPassword", message="��й�ȣ�� ����ġ�մϴ�.")
public class UserModificationForm {
	private long id;
	
	private String username;
	
	@NotEmpty(message="기존 Password를 입력해주세요")
	@Size(min=8, message = "최소8자 이상 입력해주세요.")
	private String existingPassword;
	
	@NotEmpty(message="새로운 Password를 입력해주세요")
	@Size(min=8, message = "최소8자 이상 입력해주세요.")
	private String newPassword;
	
	@NotEmpty(message="새로운 Password 중복확인을  입력해주세요")
	@Size(min=8, message = "최소8자 이상 입력해주세요.")
	private String newConfirmPassword;
	
	@NotEmpty(message="이름을 입력해주세요.")
	@Size(max=45, message = "최대 45까지 입력 가능합니다.")
	private String firstName;
	
	@NotEmpty(message="성을 입력해주세요.")
	@Size(max=45, message = "최대 45까지 입력 가능합니다.")
	private String lastName;
	
	private boolean gender;

	private Date birth;

	private SocialService signInProvider;
	
	@NotEmpty(message="여행지 타입을 선택해주세요.")
	private String[] userPreferences;
	
	
	private String year;
	private String month;
	private String date;
	
	
	public static Builder getBuilder(){
		return new Builder();
	}
	public void setBirth() throws Exception{
		String dateString = year+"/"+month+"/"+date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		birth = sdf.parse(dateString);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getExistingPassword() {
		return existingPassword;
	}
	public void setExistingPassword(String existingPassword) {
		this.existingPassword = existingPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewConfirmPassword() {
		return newConfirmPassword;
	}
	public void setNewConfirmPassword(String newConfirmPassword) {
		this.newConfirmPassword = newConfirmPassword;
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
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
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
	
	public static class Builder{
		private UserModificationForm userModificationForm;
		
		public Builder(){
			userModificationForm = new UserModificationForm();
		}
		public Builder id(long id){
			userModificationForm.id = id;
			return this;
		}
		public Builder username(String username){
			userModificationForm.username = username;
			return this;
		}
		public Builder existingPassword(String existingPassword){
			userModificationForm.existingPassword = existingPassword;
			return this;
		}
		public Builder newPassword(String newPassword){
			userModificationForm.newPassword = newPassword;
			return this;
		}
		public Builder newConfirmPassword(String newConfirmPassword){
			userModificationForm.newConfirmPassword = newConfirmPassword;
			return this;
		}
		public Builder firstName(String firstName){
			userModificationForm.firstName = firstName;
			return this;
		}
		public Builder lastName(String lastName){
			userModificationForm.lastName = lastName;
			return this;
		}
		public Builder signInProvider(SocialService signInProvider){
			userModificationForm.signInProvider = signInProvider;
			return this;
		}
		public Builder gender(boolean gender){
			userModificationForm.gender = gender;
			return this;
		}
		public Builder userPreferences(String[] userPreferences){
			userModificationForm.userPreferences = userPreferences;
			return this;
		}
		public Builder year(String year){
			userModificationForm.year = year;
			return this;
		}
		public Builder month(String month){
			userModificationForm.month = month;
			return this;
		}
		public Builder date(String date){
			userModificationForm.date = date;
			return this;
		}
		public UserModificationForm build(){
			return userModificationForm;
		}
	}
}
