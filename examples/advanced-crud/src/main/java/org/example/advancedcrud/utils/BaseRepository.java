package org.example.advancedcrud.utils;

import java.util.Optional;

public interface BaseRepository {
    <T> Optional<T> findOneById(
            Long id,
            Class<T> type
    );
    <T> Optional<T> findAllById(
            Long id,
            Class<T> type
    );
}
