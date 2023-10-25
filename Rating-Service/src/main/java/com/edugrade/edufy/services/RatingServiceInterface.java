package com.edugrade.edufy.services;

import java.util.List;

import com.edugrade.edufy.models.Rating;
import com.edugrade.edufy.models.dto.RatingDTO;

public interface RatingServiceInterface {

	RatingDTO addRating(Rating rating);

	RatingDTO updateRatingForMedia(Long ratingId, Rating rating);

	String deleteRating(Long ratingId);

	List<RatingDTO> getAllRatings();

}
