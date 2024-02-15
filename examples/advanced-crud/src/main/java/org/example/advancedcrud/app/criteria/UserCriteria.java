package org.example.advancedcrud.app.criteria;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCriteria {
    private int id;
    private String username;
}
