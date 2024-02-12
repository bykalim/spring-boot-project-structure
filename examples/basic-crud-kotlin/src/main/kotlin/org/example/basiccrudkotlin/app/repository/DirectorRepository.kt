package org.example.basiccrudkotlin.app.repository;

import org.example.basiccrudkotlin.app.entity.Director
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.rest.core.annotation.RepositoryRestResource


@RepositoryRestResource(path = "directors")
interface DirectorRepository : JpaRepository<Director, Long>, JpaSpecificationExecutor<Director> {
}