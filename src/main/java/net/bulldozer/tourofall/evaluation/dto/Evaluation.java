package net.bulldozer.tourofall.evaluation.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.bulldozer.tourofall.common.util.CheckSameUtil;
import net.bulldozer.tourofall.user.dto.User;

@Entity
@Table(name="evaluations")
public class Evaluation{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="evaluation_id")
	private long id;
	
	@JoinColumn(name="user_id")
	@ManyToOne(cascade=CascadeType.MERGE)
	private User user;
	
	@Column(name="item_id")
	private int itemId;
	
	private double score;

	public static Builder getBuilder(){
		return new Builder();
	}
	
	
	public long getId() {
		return id;
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
			oldUser.removeEvaluation(this);
		if(user != null)
			user.addEvaluation(this);
		
	}

	public int getItemId() {
		return itemId;
	}

	public void setScore(double score){
		this.score = score;
	}
	
	public double getScore() {
		return score;
	}
	
	
	public static class Builder {
		private Evaluation evaluation;
		
		public Builder(){
			evaluation = new Evaluation();
		}

		public Builder itemId(int itemId){
			evaluation.itemId = itemId;
			return this;
		}
		public Builder score(double score){
			evaluation.score = score;
			return this;
		}
		public Evaluation build(){
			return evaluation;
		}
	}
}
