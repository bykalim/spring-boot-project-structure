package org.example.advancedcrud.app.service;

import org.example.advancedcrud.app.criteria.UserCriteria;
import org.example.advancedcrud.app.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public interface UserService {
    <T> Object fetchAll(Class<T> klass, UserCriteria criteria, Pageable pageable);

    <T> Object fetchAll(Class<T> klass, UserCriteria criteria, Sort sort);

    Boolean existsByUsername(String username);

    <T> Optional<T> getOneByUsername(
            String username,
            Class<T> type
    );

    void deleteByUsername(String username);

    User update(
            String username,
            User entity
    );

    User save(User entity);
}
