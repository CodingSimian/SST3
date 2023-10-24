package com.edugrade.edufy.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.edugrade.edufy.exceptions.ResourceNotFoundException;
import com.edugrade.edufy.models.Media;
import com.edugrade.edufy.models.Rating;
import com.edugrade.edufy.models.dto.RatingDTO;
import com.edugrade.edufy.repositories.RatingRepository;
import com.edugrade.edufy.services.RatingServiceInterface;

@Service
public class RatingService implements RatingServiceInterface {

	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private RestTemplate restTemplate;

	public RatingService() {
	}

	@Override
	public RatingDTO addRating(Rating rating) {
		return getMediaDetails(ratingRepository.save(rating));
	}

	@Override
	public RatingDTO updateRatingForMedia(Long ratingId, Rating rating) {
		Rating ratingToUpdate = findRatingById(ratingId);
		ratingToUpdate.setLiked(rating.isLiked());
		return addRating(ratingToUpdate);
	}
	
	@Override
	public List<RatingDTO> getAllRatings() {
		return ratingRepository.findAll().stream().map(this::getMediaDetails).toList();
	}

	@Override
	public String deleteRating(Long ratingId) {
		Rating ratingToRemove = findRatingById(ratingId);
		ratingRepository.delete(ratingToRemove);
		return String.format("Deleted rating for media with ID: '%s'", ratingToRemove.getMediaId());
	}

	public RatingDTO getMediaDetails(Rating rating) {
		return new RatingDTO(rating.getRatingId(), getMediaDetailsFromApi(rating.getMediaId()), rating.isLiked());
	}

	private Media getMediaDetailsFromApi(String mediaId) {
		String url = "http://localhost:8080/media-service/media/" + mediaId;
		try {
			return restTemplate.getForEntity(url, Media.class).getBody();
		} catch (RestClientException e) {
			return new Media(mediaId);
		}
	}

	private Rating findRatingById(Long ratingId) {
		return ratingRepository.findById(ratingId)
				.orElseThrow(() -> new ResourceNotFoundException("Rating", "ID", ratingId));
	}

}
