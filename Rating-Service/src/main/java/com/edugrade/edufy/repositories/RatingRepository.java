package com.edugrade.edufy.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.edugrade.edufy.models.Rating;


public interface RatingRepository extends JpaRepository<Rating, Long>{
	
	@Query(value = "select u.*, r.* "
					+ "from users u "
					+ "join users_ratings ur "
					+ "on u.user_id = ur.user_user_id join rating r "
					+ "on ur.ratings_rating_id = r.rating_id "
					+ "where user_id = :userId "
					+ "and media_id = :mediaId", nativeQuery = true)
	Optional<Rating> findRatingByUserIdAndMediaId(@Param("userId") Long userId, @Param("mediaId") String mediaId);
	
	Optional<Rating> findRatingByRatingId(Long ratingId);

} 
