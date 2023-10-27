package com.edugrade.edufy.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;

import java.util.List;

public class Media3 {


       @JsonSerialize(using = ToStringSerializer.class)
        private ObjectId id;
        private String title;
        private String media_type;
        private String url;
        private String release_date;
        private List<String> genres;


        private List<String> artists;

        public Media3() {
        }

        public Media3(ObjectId id, String title, String media_type, String url, String release_date, List<String> genres, List<String> artists) {
            this.id = id;
            this.title = title;
            this.media_type = media_type;
            this.url = url;
            this.release_date = release_date;
            this.genres = genres;
            this.artists = artists;
        }

        public Media3(ObjectId id){
            this.id = id;
        }

        public ObjectId getId() {
            return id;
        }

        public void setId(ObjectId id) {
            this.id = id;
        }



        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMedia_type() {
            return media_type;
        }

        public void setMedia_type(String media_type) {
            this.media_type = media_type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public List<String> getArtists() {
            return artists;
        }

        public void setArtists(List<String> artists) {
            this.artists = artists;
        }

        @Override
        public String toString() {
            return "Media{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", mediaType='" + media_type + '\'' +
                    ", url='" + url + '\'' +
                    ", releaseDate='" + release_date + '\'' +
                    ", genres='" + genres + '\'' +
                    ", artists=" + artists +
                    '}';
        }

}
