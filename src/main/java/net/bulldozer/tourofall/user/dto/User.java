package net.bulldozer.tourofall.user.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import net.bulldozer.tourofall.answer.dto.Answer;
import net.bulldozer.tourofall.evaluation.dto.Evaluation;
import net.bulldozer.tourofall.question.dto.Question;
import net.bulldozer.tourofall.review.dto.Review;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;
	
	private String username;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	private String password;
	private boolean gender;
	private Date birth;
	
	
	@Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private Role role = Role.ROLE_USER;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "sign_in_provider", length = 20)
    private SocialService signInProvider;
	
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="user", cascade= CascadeType.ALL)
	private Collection<Review> reviews = new ArrayList<Review>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="user", cascade= CascadeType.ALL)
	private Collection<Question> questions = new ArrayList<Question>();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="user", cascade= CascadeType.ALL)
	private Collection<Answer> answers = new ArrayList<Answer>();

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="user", cascade= CascadeType.ALL)
	private Collection<Evaluation> evaluations = new ArrayList<Evaluation>();

	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="user", cascade= CascadeType.PERSIST)
	private Collection<UserPreference> userPreferences = new ArrayList<UserPreference>();
	
	public static Builder getBuilder(){
		return new Builder();
	}
	
	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public boolean getGender() {
		return gender;
	}
	public Date getBirth() {
		return birth;
	}
	public Role getRole() {
		return role;
	}
	
	public SocialService getSignInProvider(){
		return signInProvider;
	}
	
	public Collection<Review> getReviews() {
		return new ArrayList<Review>(reviews);
	}

	public void addReview(Review review){
		if(reviews.contains(review))
			return ;
		reviews.add(review);
		review.setUser(this);
	}
	public void removeReview(Review review){
		if(!reviews.contains(review))
			return ;
		reviews.remove(review);
		review.setUser(null);
	}
	
	public Collection<Question> getQuestions(){
		return new ArrayList<Question>(questions);
	}
	

	public void addQuestion(Question question){
		if(questions.contains(question))
			return ;
		questions.add(question);
		question.setUser(this);
	}
	public void removeQuestion(Question question){
		if(!questions.contains(question))
			return ;
		questions.remove(question);
		question.setUser(null);
	}
	
	public Collection<Evaluation> getEvaluations(){
		return new ArrayList<Evaluation>(evaluations);
	}
	

	public void addEvaluation(Evaluation evaluation){
		if(evaluations.contains(evaluation))
			return ;
		evaluations.add(evaluation);
		evaluation.setUser(this);
	}
	public void removeEvaluation(Evaluation evaluation){
		if(!evaluations.contains(evaluation))
			return ;
		evaluations.remove(evaluation);
		evaluation.setUser(null);
	}
	
	public Collection<Answer> getAnswers(){
		return new ArrayList<Answer>(answers);
	}
	

	public void addAnswer(Answer answer){
		if(answers.contains(answer))
			return ;
		answers.add(answer);
		answer.setUser(this);
	}
	public void removeAnswer(Answer answer){
		if(!answers.contains(answer))
			return ;
		answers.remove(answer);
		answer.setUser(null);
	}
	

	public Collection<UserPreference> getUserPreferences(){
		return new ArrayList<UserPreference>(userPreferences);
	}
	

	public void setUserPreferences(List<UserPreference> userPreferences){
		this.userPreferences.clear();
		Iterator<UserPreference> userPreferenceIter = userPreferences.iterator();
		while(userPreferenceIter.hasNext()){
			UserPreference userPreference = userPreferenceIter.next();
			this.userPreferences.add(userPreference);
			userPreference.setUser(this);
		}
	}
	
	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(getId());
        return builder.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User)) {
            return false;
        }
		User otherUser  = (User) obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.id, otherUser.getId());
        return builder.isEquals();
	}
	
	public static class Builder{
		private User user;
		
		public Builder(){
			user = new User();
			user.role = Role.ROLE_USER;
		}
		public Builder username(String username){
			user.username = username;
			return this;
		}
		public Builder password(String password){
			user.password = password;
			return this;
		}
		public Builder firstName(String firstName){
			user.firstName = firstName;
			return this;
		}
		public Builder lastName(String lastName){
			user.lastName = lastName;
			return this;
		}
		public Builder birth(Date birth){
			user.birth = birth;
			return this;
		}
		public Builder gender(boolean gender){
			user.gender = gender;
			return this;
		}
		public Builder role(Role role){
			user.role = role;
			return this;
		}
		public Builder signInProvider(SocialService signInProvider){
			user.signInProvider = signInProvider;
			return this;
		}
		public Builder userPreferences(List<UserPreference> userPreferences){
			user.setUserPreferences(userPreferences);
			return this;
		}
		public User build(){
			return user;
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", gender=" + gender + ", birth=" + birth + ", role=" + role
				+ ", signInProvider=" + signInProvider + ", reviews=" + reviews + ", questions=" + questions
				+ ", answers=" + answers + ", evaluations=" + evaluations + ", userPreferences=" + userPreferences
				+ "]";
	}

	
}
