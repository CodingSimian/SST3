package com.edugrade.edufy.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.edugrade.edufy.exceptions.ResourceNotFoundException;
import com.edugrade.edufy.models.Media;
import com.edugrade.edufy.models.PlayedMedia;
import com.edugrade.edufy.models.dto.PlayedMediaDTO;
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
	public PlayedMediaDTO addPlayedMedia(PlayedMedia playedMedia) {
		return getMediaDetails(playedMediaRepository.save(playedMedia));
	}

	@Override
	public PlayedMediaDTO updatePlayedMedia(PlayedMedia playedMedia) {
		playedMedia.increaseNumberOfPlaysByOne();
		return addPlayedMedia(playedMedia);
	}

	@Override
	public String deletePlayedMedia(String mediaId) {
		playedMediaRepository.deleteAll(findPlayedMediaByMediaId(mediaId));
		return String.format("Deleted played media with ID:'%s'", mediaId);
	}

	public PlayedMediaDTO getMediaDetails(PlayedMedia playedMedia) {
		return new PlayedMediaDTO(playedMedia.getPlayedMediaId(), getMediaDetailsFromApi(playedMedia.getMediaId()),
				playedMedia.getNumberOfPlays());
	}
	
	private List<PlayedMedia> findPlayedMediaByMediaId(String playedMediaId) throws ResourceNotFoundException{		
		return playedMediaRepository.findByMediaId(playedMediaId).filter(list -> !list.isEmpty())
				.orElseThrow(() -> new ResourceNotFoundException("Media", "ID", playedMediaId));
	}

	private Media getMediaDetailsFromApi(String mediaId) {
		String url = "http://localhost:8080/media/" + mediaId;
		try {
			return restTemplate.getForEntity(url, Media.class).getBody();
		} catch (RestClientException e) {
			return new Media(mediaId);
		}
	}

}
