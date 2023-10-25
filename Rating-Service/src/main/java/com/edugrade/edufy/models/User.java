package com.edugrade.edufy.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "user_id")
	private Long userId;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Rating> ratings;

	public User() {
	}
	
	public User(Long userId) {
		this.userId = userId;
	}

	public User(Long userId, List<Rating> ratings) {
		this(userId);
		this.ratings = ratings;
	}

//	public Long getUserRatingId() {
//		return userRatingId;
//	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void addRating(Rating rating) {
		this.ratings.add(rating);
	}

	public void removeRating(Rating rating) {
		this.ratings.remove(rating);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", ratings=" + ratings + "]";
	}

}
