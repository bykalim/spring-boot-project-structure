package org.example.advancedcrud.app.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.advancedcrud.app.entity.Movie;
import org.example.advancedcrud.app.request.movies.MovieRequest;
import org.example.advancedcrud.app.request.movies.MovieRequestHelper;
import org.example.advancedcrud.app.service.MovieService;
import org.example.advancedcrud.utils.BaseController;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController extends BaseController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<?> index(@ModelAttribute MovieRequest.IndexRequest request) {
        var helper = new MovieRequestHelper();

        // get request
        final var view = request.getView();
        final var criteria = request.parseCriteria();
        final var pageable = request.getPageRequest();

        // parse request
        final var klass = helper.parseView(view);
        final var sort = request.getSortBy() == null
                ? Sort.unsorted()
                : Sort.by(request.getSortBy());

        // get data
        final var response = pageable == null
                ? movieService.fetchAll(klass, criteria, sort)
                : movieService.fetchAll(klass, criteria, pageable);

        // return response
        return this.sendSuccess(response);
    }


    /**
     * Get connector by id
     *
     * @param id String
     * @param request  ShowRequest
     * @return ResponseEntity<Any>
     */
    @GetMapping("/{id}")
    ResponseEntity<?> show(
            @PathVariable Long id,
            @ModelAttribute
            MovieRequest.ShowRequest request
    ) {
        var helper = new MovieRequestHelper();

        final var view = request.getView();
        final var klass = helper.parseView(view);
        final var response = movieService.getOneById(id, klass);
        return this.sendSuccess(response);
    }

    /**
     * Delete connector by id
     *
     * @param id Long
     * @return ResponseEntity<Any>
     */
    @DeleteMapping("/{id}")
    ResponseEntity<?> destroy(
            @PathVariable
            Long id
    ) {
        movieService.deleteById(id);
        return this.sendSuccess();
    }

    /**
     * Create new connector
     *
     * @param request CreateRequest
     * @return ResponseEntity<Any>
     */
    @PostMapping
    ResponseEntity<?> store(
            @RequestBody
            @Validated
            MovieRequest.CreateRequest request
    ) {
        final var objectMapper = new ObjectMapper();
        final var entity = objectMapper.convertValue(request, Movie.class);
        final var response = movieService.save(entity);
        return this.sendSuccess(response);
    }

    /**
     * Update connector by id
     *
     * @param id Long
     * @param request  CreateRequest
     * @return ResponseEntity<Any>
     */
    @PatchMapping("/{id}")
    ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody JsonNode request
    ) {
        if (request.get("id") != null) throw new RuntimeException("ID is forbidden");

        final var objectMapper = new ObjectMapper();
        final var entity = objectMapper.convertValue(request, Movie.class);
        final var response = movieService.update(id, entity);
        return this.sendSuccess(response);
    }
}
