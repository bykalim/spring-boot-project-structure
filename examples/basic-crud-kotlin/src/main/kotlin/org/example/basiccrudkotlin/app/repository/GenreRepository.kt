package org.example.basiccrudkotlin.app.repository;

import org.example.basiccrudkotlin.app.entity.Genre
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "genres")
interface GenreRepository : JpaRepository<Genre, Int>, JpaSpecificationExecutor<Genre> {
}