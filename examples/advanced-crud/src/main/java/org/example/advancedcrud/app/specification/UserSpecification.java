package org.example.advancedcrud.app.specification;

import org.example.advancedcrud.app.criteria.UserCriteria;
import org.example.advancedcrud.app.entity.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.NotNull;

public class UserSpecification implements Specification<User> {

    private final UserCriteria criteria;

    public UserSpecification(UserCriteria criteria) {
        this.criteria = criteria;
    }

    @Nullable
    @Override
    public Predicate toPredicate(
            @NotNull Root<User> root,
            @NotNull CriteriaQuery<?> query,
            @NotNull CriteriaBuilder criteriaBuilder
    ) {
        final var predicates = new ArrayList<Predicate>();
        if (criteria.getUsername() != null) {
            predicates.add(criteriaBuilder.equal(root.get("username"), criteria.getUsername()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
