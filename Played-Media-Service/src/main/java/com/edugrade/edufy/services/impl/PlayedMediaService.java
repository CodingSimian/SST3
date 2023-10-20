package com.edugrade.edufy.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.edugrade.edufy.models.PlayedMedia;
import com.edugrade.edufy.models.vo.Media;
import com.edugrade.edufy.repositories.PlayedMediaRepository;
import com.edugrade.edufy.services.PlayedMediaServiceInterface;

@Service
public class PlayedMediaService implements PlayedMediaServiceInterface {

	@Autowired
	private PlayedMediaRepository playedMediaRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	public PlayedMediaService() {
	}

	@Override
	public List<PlayedMedia> getMostPlayedMedia(Long userId) {
		return null;
	}

	@Override
	public PlayedMedia addPlayedMedia(PlayedMedia playedMedia) {
		return playedMediaRepository.save(playedMedia);
	}

	@Override
	public PlayedMedia updatePlayedMedia(PlayedMedia playedMedia) {
		playedMedia.increaseNumberOfPlaysByOne();
		return addPlayedMedia(playedMedia);
	}
	
	public Media getMediaDetails(PlayedMedia playedMedia) {
		String url = "http://localhost:8080/media-service/api/v1/media/" + playedMedia.getMediaId();
		System.out.println(url);
		Media media = restTemplate.getForObject(url, Media.class);
		media.setNumberOfPlays(playedMedia.getNumberOfPlays());
		System.out.println(media);
		return media;
	}

	@Override
	public String deletePlayedMedia(String mediaId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
