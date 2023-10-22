package com.edugrade.edufy.services;


import com.edugrade.edufy.models.PlayedMedia;
import com.edugrade.edufy.models.dto.PlayedMediaDTO;

public interface PlayedMediaServiceInterface {

	public PlayedMediaDTO addPlayedMedia(PlayedMedia playedMedia);

	public PlayedMediaDTO updatePlayedMedia(PlayedMedia playedMedia);
	
	public String deletePlayedMedia(String mediaId);

}
