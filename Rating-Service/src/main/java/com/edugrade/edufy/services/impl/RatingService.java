package com.edugrade.edufy.services.impl;

import java.util.*;

import com.edugrade.edufy.models.Media3;
import com.edugrade.edufy.models.dto.ObjectIdDto;
import com.edugrade.edufy.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
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

	@Value("${app.media.url}")
	private String mediaUrl;

	@Autowired
	private RatingRepository ratingRepository;


	@Autowired
	private UserRepository userRepository;

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

	public List<Media3> getRecommendationsForUser(Long userId) {
		/*
		List<Rating> fakeRatings = new ArrayList<>();
		Rating fakerating1 = new Rating();
		fakerating1.setMediaId(new ObjectId("652c1f10594ebc43eab1fd9a"));
		Rating fakerating2 = new Rating();
		fakerating2.setMediaId(new ObjectId("652538d38df9fc72388baddf"));
		Rating fakerating3 = new Rating();
		fakerating3.setMediaId(new ObjectId("65259b408df9fc72388bae09"));
		fakeRatings.add(fakerating1);
		fakeRatings.add(fakerating2);
		fakeRatings.add(fakerating3);
		*/

		List<Rating> dbRatings = userRepository.findByUserId(userId).get().getRatings();

		List<Media3> ratedMedia3 = fetchMedia(dbRatings);
		List<String> topGenres = topGenre(ratedMedia3);

		List<Media3> genre1 = fetchMediaByGenre(topGenres.get(0));
		List<Media3> genre2 = fetchMediaByGenre(topGenres.get(1));
		List<Media3> genre3 = fetchMediaByGenre(topGenres.get(2));

		List<Media3> playedMedia3 = fetchPlayedMedia(userId);

		List<ObjectId> playedMediaId = new ArrayList<>();
		for(Media3 media3 : playedMedia3){
			playedMediaId.add(media3.getId());

		}
		List<Media3> recommended = sort(genre1,genre2,genre3,playedMediaId);
		//if(recommended.size() == 10){
		return recommended;
		/*}else{
			throw new ResourceNotFoundException();
		}
		 */



	}

	public RatingDTO getMediaDetails(Rating rating) {
		return new RatingDTO(rating.getRatingId(), getMediaDetailsFromApi(rating.getMediaId()), rating.isLiked());
	}

	public Media getMediaDetailsFromApi(String mediaId) {
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

	public List<String> getGenreTest(){
		return topGenre(fetchMedia());
	}

	private List<Media3> sort(List<Media3> genre1, List<Media3> genre2, List<Media3> genre3, List<ObjectId> playedMedia){
		List<Media3> recommendations = new ArrayList<>(10);
		recommendations.addAll(getSongs(genre1,playedMedia,4));
		recommendations.addAll(getSongs(genre2,playedMedia,3));
		recommendations.addAll(getSongs(genre3,playedMedia,3));
		return recommendations;

	}

	private List<Media3> getSongs(List<Media3> genre, List<ObjectId> playedMedia, int amount){
		List<Media3> media3List = new ArrayList<>(3);
		int count = 0;
		for(Media3 media3 :genre){
			if(count>=amount){
				break;
			}else if (!playedMedia.contains(media3.getId())){
				media3List.add(media3);
				count ++;
			}

		}
		return media3List;
	}



	private List<Media3> fetchMedia(){
		try{
			ResponseEntity<Media3[]> response = restTemplate.getForEntity(mediaUrl+"/everything", Media3[].class);
			if(response != null && response.hasBody()) {


				return Arrays.asList(response.getBody());
			}
		}catch (Exception exc){
			exc.printStackTrace();
		}
		throw new NoSuchElementException();
	}

	public List<Media3> fetchMedia(List<Rating> ratedMedia){

		List<ObjectIdDto> requestedMedia = new ArrayList<>();
		for(Rating rating:ratedMedia){
			requestedMedia.add(new ObjectIdDto(new ObjectId(rating.getMediaId())));
		}



		HttpEntity<List<ObjectIdDto>> request = new HttpEntity<>(requestedMedia);
		try{
			ResponseEntity<Media3[]> response = restTemplate.postForEntity(mediaUrl+"/media/list",request, Media3[].class);
			if(response.hasBody()) {


				return Arrays.asList(response.getBody());
			}


		}catch (Exception exc){
			exc.printStackTrace();
		}
		throw new NoSuchElementException();

	}

	public List<Media3> fetchMediaByGenre(String genre){
		try{
			ResponseEntity<Media3[]> response = restTemplate.getForEntity(mediaUrl +"/genre/"+genre, Media3[].class);
			if( response.hasBody()) {


				return Arrays.asList(response.getBody());
			}


		}catch (Exception exc){
			exc.printStackTrace();
		}
		throw new NoSuchElementException();
	}

	public List<Media3> fetchMediaByGenres(List<String> genres){
		String genresUrl = "";
		for(String genre:genres){
			genresUrl += ","+genre;
		}

		try{
			ResponseEntity<Media3[]> response = restTemplate.getForEntity(mediaUrl +"/genres/"+genresUrl, Media3[].class);
			if(response.hasBody()) {


				return Arrays.asList(response.getBody());
			}


		}catch (Exception exc){
			exc.printStackTrace();
		}
		throw new NoSuchElementException();
	}






	private List<Media3> fetchPlayedMedia(Long userId) {

		try{
			ResponseEntity<Media3[]> response = restTemplate.getForEntity("http://localhost:8080//played-media-service/api/v1/users/" + userId + "/playedmedia", Media3[].class);
			if( response.hasBody()) {
				return Arrays.asList(response.getBody());
			}


		}catch (Exception exc){
			exc.printStackTrace();
		}
		throw new NoSuchElementException();
	}




	private List<String> topGenre(List<Media3> media3List) {
		Map<String, Integer> genres2 = new HashMap<>();
		for (Media3 media3 : media3List) {
			for (String genre : media3.getGenres()) {
				if (genres2.containsKey(genre)) {
					genres2.replace(genre, genres2.get(genre) + 1);
				} else {
					genres2.put(genre, 1);
				}

			}
		}

		ArrayList<String> genre = new ArrayList<>();
		ArrayList<Integer> count = new ArrayList<>();


		int min = 0;
		int current = 0;
		int itteration = 0;
		for (String key : genres2.keySet()) {
			current = genres2.get(key);
			if (itteration > 2 && current > min) {
				int index = count.indexOf(min);
				genre.remove(index);
				genre.add(index, key);
				count.remove(index);
				count.add(index, current);
			} else if (itteration <= 2) {
				genre.add(itteration, key);
				count.add(itteration, current);
				if (current > min) {
					min = current;
				}


			}
			itteration++;

		}


		return genre;


	}



}
