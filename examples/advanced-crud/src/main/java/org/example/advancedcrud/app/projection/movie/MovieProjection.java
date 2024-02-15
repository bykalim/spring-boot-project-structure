package org.example.advancedcrud.app.projection.movie;

import org.example.advancedcrud.app.entity.Genre;

public interface MovieProjection {
    interface DefaultView {
        Long getId();
        String getTitle();
    }

    interface ExtendedView {
        String getId();
        String getTitle();
        String getDescription();
    }
}