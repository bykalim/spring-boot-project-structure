package org.example.advancedcrud.app.service.impl;

import org.example.advancedcrud.app.criteria.MovieCriteria;
import org.example.advancedcrud.app.entity.Movie;
import org.example.advancedcrud.app.exception.NoSuchMovieException;
import org.example.advancedcrud.app.repository.MovieRepository;
import org.example.advancedcrud.app.service.MovieService;
import org.example.advancedcrud.app.specification.MovieSpecification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public <T> Object fetchAll(Class<T> klass, MovieCriteria criteria, Pageable pageable) {
        final var specification = new MovieSpecification(criteria);
        return movieRepository.findBy(specification, it -> it.as(klass).sortBy(pageable.getSort()).page(pageable));
    }

    @Override
    public <T> Object fetchAll(Class<T> klass, MovieCriteria criteria, Sort sort) {
        final var specification = new MovieSpecification(criteria);
        return movieRepository.findBy(specification, it -> it.as(klass).sortBy(sort).all());
    }

    @Override
    public Boolean existsById(Long id) {
        return movieRepository.existsById(id);
    }


    public <T> Optional<T> getOneById(
            Long id,
            Class<T> type
    ) {
        final var data = movieRepository.findOneById(id, type);
        if (data.isEmpty()) throw new NoSuchMovieException();
        return data;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Movie update(
            Long id,
            Movie entity
    ) {
        if (!existsById(id)) throw new NoSuchMovieException();
        return movieRepository.save(entity);
    }

    @Override
    public Movie save(final Movie entity) {
        return movieRepository.save(entity);
    }
}
