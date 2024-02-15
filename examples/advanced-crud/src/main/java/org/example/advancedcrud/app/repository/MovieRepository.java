package org.example.advancedcrud.app.repository;

import org.example.advancedcrud.app.entity.Movie;
import org.example.advancedcrud.utils.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie>, BaseRepository {

}