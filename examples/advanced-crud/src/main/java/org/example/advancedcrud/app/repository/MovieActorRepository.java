package org.example.advancedcrud.app.repository;

import org.example.advancedcrud.app.entity.MovieActor;
import org.example.advancedcrud.app.entity.MovieActorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovieActorRepository extends JpaRepository<MovieActor, MovieActorId>,
        JpaSpecificationExecutor<MovieActor> {
}