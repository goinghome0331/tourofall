package net.bulldozer.tourofall.user.dto;

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

@Entity
@Table(name="user_preferences")
public class UserPreference {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_preference_id")
	private long id;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="item_category_code")
	private String itemCategoryCode;

	
	public static Builder getBuilder(){
		return new Builder();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getItemCategoryCode() {
		return itemCategoryCode;
	}

	public void setItemCategoryCode(String itemCategoryCode) {
		this.itemCategoryCode = itemCategoryCode;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(getId());
        return builder.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof UserPreference)) {
            return false;
        }
		UserPreference otherUserPreference  = (UserPreference) obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.id, otherUserPreference.getId());
        return builder.isEquals();
	}
	
	public static class Builder{
		private UserPreference  userPreference;
		
		public Builder(){
			userPreference = new UserPreference();
		}
		public Builder itemCategoryCode(String itemCategoryCode){
			userPreference.itemCategoryCode = itemCategoryCode;
			return this;
		}
		public UserPreference build(){
			return userPreference;
		}
	}
}
