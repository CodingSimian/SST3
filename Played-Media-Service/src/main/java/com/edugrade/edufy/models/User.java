package com.edugrade.edufy.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@Column(name = "user_id")
	private Long userId;
	@OneToMany(cascade = CascadeType.ALL)
	@OrderBy("numberOfPlays DESC")
	private List<PlayedMedia> playedMedia;

	public User() {
	}

	public User(Long userId) {
		this.userId = userId;
	}

	public User(Long userId, List<PlayedMedia> playedMedia) {
		this(userId);
		this.playedMedia = playedMedia;
	}

	public Long getUserId() {
		return userId;
	}

	public List<PlayedMedia> getPlayedMedia() {
		return playedMedia;
	}

	public void setPlayedMedia(List<PlayedMedia> playedMedia) {
		this.playedMedia = playedMedia;
	}

	public void addPlayedMedia(PlayedMedia playedMedia) {
		this.playedMedia.add(playedMedia);
	}

	public PlayedMedia getPlayedMediaById(Long playedMediaId) {
		return this.playedMedia.get(playedMediaId.intValue());
	}

	public void removePlayedMedia(PlayedMedia playedMedia) {
		this.playedMedia.remove(playedMedia);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", playedMedia=" + playedMedia + "]";
	}

}
