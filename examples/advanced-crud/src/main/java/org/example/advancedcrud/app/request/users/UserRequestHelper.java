package org.example.advancedcrud.app.request.users;

import org.example.advancedcrud.app.criteria.UserCriteria;
import org.example.advancedcrud.app.projection.user.UserProjection;
import org.example.advancedcrud.app.specification.UserSpecification;

import jakarta.annotation.Nullable;

public class UserRequestHelper {
    public Class<?> parseView(@Nullable String view) {
        if (view == null) return UserProjection.DefaultView.class;

        return switch (view) {
            default -> UserProjection.DefaultView.class;
        };
    }

    public UserSpecification getSpecification(UserCriteria criteria) {
        return new UserSpecification(criteria);
    }
}
