package org.example.advancedcrud.app.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.advancedcrud.app.entity.User;
import org.example.advancedcrud.app.request.users.UserRequestHelper;
import org.example.advancedcrud.app.request.users.UserRequest;
import org.example.advancedcrud.app.service.UserService;
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
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> index(@ModelAttribute UserRequest.IndexRequest request) {
        var helper = new UserRequestHelper();

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
                ? userService.fetchAll(klass, criteria, sort)
                : userService.fetchAll(klass, criteria, pageable);

        // return response
        return this.sendSuccess(response);
    }


    /**
     * Get connector by id
     *
     * @param username String
     * @param request  ShowRequest
     * @return ResponseEntity<Any>
     */
    @GetMapping("/{username}")
    ResponseEntity<?> show(
            @PathVariable String username,
            @ModelAttribute
            UserRequest.ShowRequest request
    ) {
        var helper = new UserRequestHelper();

        final var view = request.getView();
        final var klass = helper.parseView(view);
        final var response = userService.getOneByUsername(username, klass);
        return this.sendSuccess(response);
    }

    /**
     * Delete connector by id
     *
     * @param username String
     * @return ResponseEntity<Any>
     */
    @DeleteMapping("/{username}")
    ResponseEntity<?> destroy(
            @PathVariable
            String username
    ) {
        userService.deleteByUsername(username);
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
            UserRequest.CreateRequest request
    ) {
        final var objectMapper = new ObjectMapper();
        final var entity = objectMapper.convertValue(request, User.class);
        final var response = userService.save(entity);
        return this.sendSuccess(response);
    }

    /**
     * Update connector by id
     *
     * @param username String
     * @param request  CreateRequest
     * @return ResponseEntity<Any>
     */
    @PatchMapping("/{username}")
    ResponseEntity<?> update(
            @PathVariable String username,
            @RequestBody JsonNode request
    ) {
        if (request.get("id") != null) throw new RuntimeException("ID is forbidden");

        final var objectMapper = new ObjectMapper();
        final var entity = objectMapper.convertValue(request, User.class);
        final var response = userService.update(username, entity);
        return this.sendSuccess(response);
    }
}
