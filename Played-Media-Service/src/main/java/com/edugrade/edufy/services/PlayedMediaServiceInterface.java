package com.edugrade.edufy.services;

import java.util.List;

import com.edugrade.edufy.models.PlayedMedia;

public interface PlayedMediaServiceInterface {

	public List<PlayedMedia> getMostPlayedMedia(Long userId);

	public PlayedMedia addPlayedMedia(PlayedMedia playedMedia);

	public PlayedMedia updatePlayedMedia(PlayedMedia playedMedia);
	
	public String deletePlayedMedia(String mediaId);

}
