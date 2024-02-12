package org.example.basiccrudkotlin.app.repository;

import org.example.basiccrudkotlin.app.entity.Review
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "reviews")
interface ReviewRepository : JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {
}