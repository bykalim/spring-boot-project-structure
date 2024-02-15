package org.example.advancedcrud.app.request.movies;

import org.example.advancedcrud.app.criteria.MovieCriteria;
import org.example.advancedcrud.app.projection.movie.MovieProjection;
import org.example.advancedcrud.app.specification.MovieSpecification;

import jakarta.annotation.Nullable;

public class MovieRequestHelper {
    public Class<?> parseView(@Nullable String view) {
        if (view == null) return MovieProjection.DefaultView.class;

        return switch (view) {
            case "extended" -> MovieProjection.ExtendedView.class;
            default -> MovieProjection.DefaultView.class;
        };
    }

    public MovieSpecification getSpecification(MovieCriteria criteria) {
        return new MovieSpecification(criteria);
    }
}
