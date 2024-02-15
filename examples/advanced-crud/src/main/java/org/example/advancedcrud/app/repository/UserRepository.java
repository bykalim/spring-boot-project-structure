package org.example.advancedcrud.app.repository;

import org.example.advancedcrud.app.entity.User;
import org.example.advancedcrud.utils.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> , BaseRepository {
    <T> Optional<T> findOneByUsername(
            String username,
            Class<T> type
    );

    boolean existsByUsername(String username);
    Optional<User> findUserByUsername(String username);

    void deleteUserByUsername(String username);
}