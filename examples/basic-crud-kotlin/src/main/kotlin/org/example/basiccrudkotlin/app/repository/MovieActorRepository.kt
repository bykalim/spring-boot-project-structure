package org.example.basiccrudkotlin.app.repository;

import org.example.basiccrudkotlin.app.entity.MovieActor
import org.example.basiccrudkotlin.app.entity.MovieActorId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.rest.core.annotation.RepositoryRestResource


@RepositoryRestResource(path = "movieActors")
interface MovieActorRepository : JpaRepository<MovieActor, MovieActorId>, JpaSpecificationExecutor<MovieActor> {
}