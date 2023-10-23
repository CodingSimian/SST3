package com.edugrade.edufy.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;

@Entity
public class PlayedMedia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long playedMediaId;
	@Length(min = 24, max = 24, message = "ObjectId lenght must be 24-chars")
	private String mediaId;
	@Column(columnDefinition = "INT DEFAULT 1")
	private int numberOfPlays = 1;

	public PlayedMedia() {
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public Long getPlayedMediaId() {
		return playedMediaId;
	}

	public int getNumberOfPlays() {
		return numberOfPlays;
	}

	public void setNumberOfPlays(int numberOfPlays) {
		this.numberOfPlays = numberOfPlays;
	}

	public void increaseNumberOfPlaysByOne() {
		this.numberOfPlays++;
	}

	@Override
	public String toString() {
		return "PlayedMedia [playedMediaId=" + playedMediaId + ", mediaId=" + mediaId + ", numberOfPlays="
				+ numberOfPlays + "]";
	}

}
