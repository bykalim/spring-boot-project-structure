package org.example.basiccrudkotlin.app.repository;

import org.example.basiccrudkotlin.app.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource
import org.springframework.stereotype.Repository

@RepositoryRestResource(path = "users")
interface UserRepository : JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}