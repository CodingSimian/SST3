package com.edugrade.edufy.models.dto;

import com.edugrade.edufy.models.Media;

public record PlayedMediaDTO(
		Long playedMediaId,
		Media media,
		int numberOfPlays) {
}
