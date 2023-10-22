package com.edugrade.edufy.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edugrade.edufy.models.PlayedMedia;

public interface PlayedMediaRepository extends JpaRepository<PlayedMedia, Long> {
	
	Optional<List<PlayedMedia>> findByMediaId(String mediaId);

}
