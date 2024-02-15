package org.example.advancedcrud.app.service.impl;

import org.example.advancedcrud.app.criteria.UserCriteria;
import org.example.advancedcrud.app.entity.User;
import org.example.advancedcrud.app.exception.NoSuchUserException;
import org.example.advancedcrud.app.repository.UserRepository;
import org.example.advancedcrud.app.service.UserService;
import org.example.advancedcrud.app.specification.UserSpecification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public <T> Object fetchAll(Class<T> klass, UserCriteria criteria, Pageable pageable) {
        final var specification = new UserSpecification(criteria);
        return userRepository.findBy(specification, it -> it.as(klass).sortBy(pageable.getSort()).page(pageable));
    }

    @Override
    public <T> Object fetchAll(Class<T> klass, UserCriteria criteria, Sort sort) {
        final var specification = new UserSpecification(criteria);
        return userRepository.findBy(specification, it -> it.as(klass).sortBy(sort).all());
    }

    @Override
    public Boolean existsByUsername(final String username) {
        return userRepository.existsByUsername(username);
    }

    public <T> Optional<T> getOneById(
            Long id,
            Class<T> type
    ) {
        final var data = userRepository.findOneById(id, type);
        if (data.isEmpty()) throw new NoSuchUserException();
        return data;
    }

    @Override
    public <T> Optional<T> getOneByUsername(
            final String username,
            Class<T> type
    ) {
        final var data = userRepository.findOneByUsername(username, type);
        if (data.isEmpty()) throw new NoSuchUserException();
        return data;
    }

    @Override
    public void deleteByUsername(String username) {
        if (!userRepository.existsByUsername(username)) throw new NoSuchUserException();
        userRepository.deleteUserByUsername(username);
    }

    @Override
    public User update(
            String username,
            User entity
    ) {
        if (!existsByUsername(username)) throw new NoSuchUserException();
        return userRepository.save(entity);
    }

    @Override
    public User save(final User entity) {
        return userRepository.save(entity);
    }

}