package com.edugrade.edufy.models.dto;

import com.edugrade.edufy.models.Media;

public record RatingDTO(
		Long ratingId, 
		Media media, 
		boolean liked) {
}
