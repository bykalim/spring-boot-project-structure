package org.example.advancedcrud.app.request.users;


import org.example.advancedcrud.app.criteria.UserCriteria;
import org.example.advancedcrud.utils.BaseRequest;

import lombok.Getter;
import lombok.Setter;

public class UserRequest {

    @Getter
    @Setter
    public static class IndexRequest extends BaseRequest {
        private String username;

        public UserCriteria parseCriteria() {
            return UserCriteria
                    .builder()
                    .username(username)
                    .build();
        }
    }

    public static class ShowRequest extends BaseRequest {
        public UserCriteria parseCriteria() {
            return UserCriteria.builder().build();
        }
    }

    @Getter
    @Setter
    public static class CreateRequest {

    }
}
