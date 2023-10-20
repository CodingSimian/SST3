package com.edugrade.edufy.models.vo;

public class Media {
	private String mediaId;
	private String title;
	private String mediaType;
	private String url;
	private String releaseDate;
	private int numberOfPlays;

	public int getNumberOfPlays() {
		return numberOfPlays;
	}

	public void setNumberOfPlays(int numberOfPlays) {
		this.numberOfPlays = numberOfPlays;
	}

	public Media() {
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "Media [mediaId=" + mediaId + ", title=" + title + ", mediaType=" + mediaType + ", url=" + url
				+ ", releaseDate=" + releaseDate + ", numberOfPlays=" + numberOfPlays + "]";
	}

}
