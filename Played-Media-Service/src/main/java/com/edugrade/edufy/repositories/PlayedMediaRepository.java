package com.edugrade.edufy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edugrade.edufy.models.PlayedMedia;

public interface PlayedMediaRepository extends JpaRepository<PlayedMedia, Long> {
	/*
	@Query(value = "SELECT pm.* FROM PLAYED_MEDIA pm JOIN USERS_PLAYED_MEDIA upm "
			+ "ON pm.played_media_id = upm.played_media_played_media_id "
			+ "WHERE upm.user_user_id = :userId "
			+ "AND pm.number_of_plays = (SELECT MAX(pm2.number_of_plays) "
			+ "FROM PLAYED_MEDIA pm2 JOIN USERS_PLAYED_MEDIA upm2 "
			+ "ON pm2.played_media_id = upm2.played_media_played_media_id "
			+ "WHERE upm2.user_user_id = :userId)", nativeQuery = true)
	List<PlayedMedia> findMostPlayedMediaForUser(@Param("userId") Long userId);
	*/

}
