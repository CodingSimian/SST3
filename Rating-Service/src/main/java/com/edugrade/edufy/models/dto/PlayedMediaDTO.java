package com.edugrade.edufy.models.dto;

import com.edugrade.edufy.models.Media;
import com.edugrade.edufy.models.Media3;

public record PlayedMediaDTO(
		Long playedMediaId,
		Media media,
		int numberOfPlays) {
}
