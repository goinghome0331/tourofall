package net.bulldozer.tourofall.answer.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import net.bulldozer.tourofall.common.util.CheckSameUtil;
import net.bulldozer.tourofall.question.dto.Question;
import net.bulldozer.tourofall.user.dto.User;

@Entity
@Table(name="answers")
public class Answer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="answer_id")
	private long id;
	
	private String content;
	
	@Column(name="created_date")
	private Date createdDate = new Date();
	
	
	@JoinColumn(name="user_id")
	@ManyToOne(cascade=CascadeType.MERGE)
	private User user;
	
	
	@JoinColumn(name="question_id")
	@ManyToOne(cascade=CascadeType.MERGE)
	private Question question;

	public Answer(){}
	
	public static Builder getBuilder(){
		return new Builder();
	}
	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public Date getCreatedDate() {
		return createdDate;
	}


	public User getUser() {
		return user;
	}
	
	public void setQuestion(Question newQuestion) {
		if(CheckSameUtil.sameAsFormerQuestion(question, newQuestion))
			return ;
		Question oldQuestion = this.question;
		this.question = newQuestion;
		if(oldQuestion != null){
			oldQuestion.removeAnswer(this);
		}
		if(question != null){
			question.addAnswer(this);
		}
	}

	public void setUser(User newUser) {
		if(CheckSameUtil.sameAsFormerUser(user, newUser))
			return ;
		User oldUser = this.user;
		this.user = newUser;
		if(oldUser != null)
			oldUser.removeAnswer(this);
		if(user != null)
			user.addAnswer(this);
		
	}

	public Question getQuestion() {
		return question;
	}

	
	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(getId());
        return builder.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Answer)) {
            return false;
        }
        Answer otherAnswer  = (Answer) obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.id, otherAnswer.getId());
        return builder.isEquals();
	}
	public static class Builder{
		Answer answer;
		public Builder(){
			answer = new Answer();
		}
		
		public Builder content(String content){
			answer.content = content;
			return this;
		}
		public Answer build(){
			return answer;
		}
	}
}
