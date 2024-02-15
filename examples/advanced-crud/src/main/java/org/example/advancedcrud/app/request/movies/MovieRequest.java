package org.example.advancedcrud.app.request.movies;


import org.example.advancedcrud.app.criteria.MovieCriteria;
import org.example.advancedcrud.utils.BaseRequest;

import lombok.Getter;
import lombok.Setter;

public class MovieRequest {

    @Getter
    @Setter
    public static class IndexRequest extends BaseRequest {
        private String title;

        public MovieCriteria parseCriteria() {
            return MovieCriteria
                    .builder()
                    .title(title)
                    .build();
        }
    }

    public static class ShowRequest extends BaseRequest {
        public MovieCriteria parseCriteria() {
            return MovieCriteria.builder().build();
        }
    }

    @Getter
    @Setter
    public static class CreateRequest {

    }
}
