
package org.example.advancedcrud.app.specification;

import org.example.advancedcrud.app.criteria.MovieCriteria;
import org.example.advancedcrud.app.entity.Movie;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.NotNull;

public class MovieSpecification implements Specification<Movie> {

    private final MovieCriteria criteria;

    public MovieSpecification(MovieCriteria criteria) {
        this.criteria = criteria;
    }

    @Nullable
    @Override
    public Predicate toPredicate(
            @NotNull Root<Movie> root,
            @NotNull CriteriaQuery<?> query,
            @NotNull CriteriaBuilder criteriaBuilder
    ) {
        final var predicates = new ArrayList<Predicate>();
        if (criteria.getTitle() != null) {
            predicates.add(criteriaBuilder.equal(root.get("title"), criteria.getTitle()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
