package org.example.basiccrudkotlin.app.repository;

import org.example.basiccrudkotlin.app.entity.Movie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "movies")
interface MovieRepository : JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
}