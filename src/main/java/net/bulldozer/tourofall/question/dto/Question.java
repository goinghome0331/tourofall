package net.bulldozer.tourofall.question.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import net.bulldozer.tourofall.answer.dto.Answer;
import net.bulldozer.tourofall.common.util.CheckSameUtil;
import net.bulldozer.tourofall.user.dto.User;

@Entity
@Table(name="questions")
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="question_id")
	private long id;
	
	private String title;
	
	private String content;
	
	@Column(name="created_date")
	private Date createdDate = new Date();
	
	private int visitor;
	
	
	@JoinColumn(name="user_id")
	@ManyToOne(cascade=CascadeType.MERGE)
	private User user;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="question", cascade=CascadeType.ALL)
	private Collection<Answer> answers = new ArrayList<Answer>();
	
	@Column(name="item_id")
	private int itemId;
	
	public static Builder getBuilder(){
		return new Builder();
	}
	
	public long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}

	
	public Date getCreatedDate() {
		return createdDate;
	}
	
	
	public int getVisitor() {
		return visitor;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User newUser) {
		if(CheckSameUtil.sameAsFormerUser(user, newUser))
			return ;
		User oldUser = this.user;
		this.user = newUser;
		if(oldUser != null)
			oldUser.removeQuestion(this);
		if(user != null)
			user.addQuestion(this);
	}
	
	public Collection<Answer> getAnswers() {
		return new ArrayList<Answer>(answers);
	}
	
	public void addAnswer(Answer answer){
		if(answers.contains(answer))
			return ;
		answers.add(answer);
		answer.setQuestion(this);
	}
	
	public void removeAnswer(Answer answer){
		if(answers.contains(answer))
			return ;
		answers.remove(answer);
		answer.setQuestion(null);
	}
	
	public void incrementVisitor(){
		visitor++;
	}
	
	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(getId());
        return builder.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Question)) {
            return false;
        }
		Question otherQuestion  = (Question) obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.id, otherQuestion.getId());
        return builder.isEquals();
	}
	
	public static class Builder{
		Question question;
		public Builder(){
			question = new Question();
		}
		public Builder title(String title){
			question.title = title;
			return this;
		}
		public Builder content(String content){
			question.content = content;
			return this;
		}
		public Builder itemId(int itemId){
			question.itemId = itemId;
			return this;
		}
		public Question build(){
			return question;
		}
	}
	
}
