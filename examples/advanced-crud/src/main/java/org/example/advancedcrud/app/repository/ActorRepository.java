package org.example.advancedcrud.app.repository;

import org.example.advancedcrud.app.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ActorRepository extends JpaRepository<Actor, Long>, JpaSpecificationExecutor<Actor> {
}