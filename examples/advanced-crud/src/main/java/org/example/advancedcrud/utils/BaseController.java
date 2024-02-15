package org.example.advancedcrud.utils;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;

public class BaseController {

    private final HttpHeaders headers;

    public BaseController() {
        headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
    }


    public ResponseEntity<?> sendSuccess() {
        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> sendSuccess(Object data, String message, HttpStatus status) {
        var map = new LinkedHashMap<String, Object>();
        map.put("data", data);
        map.put("message", message);
        return new ResponseEntity<>(map, headers, status);
    }

    public ResponseEntity<?> sendSuccess(Object data) {
        return this.sendSuccess(data, "Data has been retrieved successfully", HttpStatus.OK);
    }

    public ResponseEntity<?> sendSuccess(Object data, String message) {
        return this.sendSuccess(data, message, HttpStatus.OK);
    }

    public ResponseEntity<?> sendError(Throwable e, String message, HttpStatus status) {
        var map = new LinkedHashMap<String, Object>();
        map.put("errors", e);
        map.put("message", message);
        return new ResponseEntity<>(map, headers, status);
    }

    public ResponseEntity<?> sendError(String message, HttpStatus status) {
        return this.sendError(new Throwable(), message, status);
    }

    public ResponseEntity<?> sendError(Throwable e) {
        return this.sendError(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<?> sendError(Throwable e, HttpStatus status) {
        return this.sendError(e, "Something went wrong", status);
    }

}
