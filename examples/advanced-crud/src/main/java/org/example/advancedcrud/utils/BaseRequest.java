package org.example.advancedcrud.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BaseRequest {
    @Min(1) int page = 1;

    @Min(5) int limit = 20;

    @Nullable
    String sortBy = null;

    @Nullable
    Boolean pagination = true;

    @Nullable
    String view = null;

    @Nullable
    List<String> include = null;

    @JsonIgnore
    public Pageable getPageRequest() {
        if (Boolean.FALSE.equals(pagination)) return null;
        return PageRequest.of(page - 1, limit, sortBy != null ? Sort.by(sortBy) : Sort.unsorted());
    }

}
