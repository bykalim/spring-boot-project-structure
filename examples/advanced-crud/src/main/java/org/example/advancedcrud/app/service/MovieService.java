package org.example.advancedcrud.app.service;

import org.example.advancedcrud.app.criteria.MovieCriteria;
import org.example.advancedcrud.app.entity.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public interface MovieService {

    <T> Object fetchAll(Class<T> klass, MovieCriteria criteria, Pageable pageable);

    <T> Object fetchAll(Class<T> klass, MovieCriteria criteria, Sort sort);

    Boolean existsById(Long id);

    <T> Optional<T> getOneById(
            Long id,
            Class<T> type
    );

    void deleteById(Long id);

    Movie update(
            Long id,
            Movie entity
    );

    Movie save(Movie entity);
}
