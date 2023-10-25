package com.edugrade.edufy.models.dto;

import java.util.List;

public record UserDTO(
		Long userId,
		List<PlayedMediaDTO> playedMedia) {
}
