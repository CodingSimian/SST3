package com.edugrade.edufy.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;

@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ratingId;
	@Length(min = 24, max = 24, message = "ObjectId lenght must be 24-chars")
	private String mediaId;
	private boolean liked;

	public Rating() {
	}

	public Rating(String mediaId, boolean liked) {
		this.mediaId = mediaId;
		this.liked = liked;
	}

	public Long getRatingId() {
		return ratingId;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}

	@Override
	public String toString() {
		return "Rating [ratingId=" + ratingId + ", mediaId=" + mediaId + ", liked=" + liked + "]";
	}

}
