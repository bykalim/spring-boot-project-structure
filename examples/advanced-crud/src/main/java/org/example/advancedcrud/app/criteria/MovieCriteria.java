package org.example.advancedcrud.app.criteria;

import org.example.advancedcrud.app.entity.Director;
import org.example.advancedcrud.app.entity.Genre;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieCriteria {
    private String title;
    private String description;
    private LocalDate releaseDate;
//    private Genre genre;
//    private Director director;
}
