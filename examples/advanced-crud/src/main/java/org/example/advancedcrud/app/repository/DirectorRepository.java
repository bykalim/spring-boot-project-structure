package org.example.advancedcrud.app.repository;

import org.example.advancedcrud.app.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DirectorRepository extends JpaRepository<Director, Long>, JpaSpecificationExecutor<Director> {
}