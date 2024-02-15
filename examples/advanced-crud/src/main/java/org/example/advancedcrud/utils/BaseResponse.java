package org.example.advancedcrud.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * BaseResponse is a utility class that provides a way to send a response with a data, message, and status
 */
public class BaseResponse {
    /**
     * @param data    The data to be sent in the response
     * @param message The message to be sent in the response
     * @param status  The status of the response
     * @return ResponseEntity with the data, message, and status
     */
    public static ResponseEntity<?> makeResponse(Object data, String message, HttpStatus status) {
        var responseBody = Map.of(
                "data", data,
                "message", message
        );
        return new ResponseEntity<>(responseBody, status);
    }

    /**
     * @param data The data to be sent in the response
     * @return ResponseEntity with the data and a message
     */
    public static ResponseEntity<?> sendSuccess(Object data) {
        var responseBody = Map.of(
                "data", data,
                "message", "Success"
        );
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    /**
     * @param data    The data to be sent in the response
     * @param message The message to be sent in the response
     * @return ResponseEntity with the data and message
     */
    public static ResponseEntity<?> sendSuccess(Object data, String message) {
        var responseBody = Map.of(
                "data", data,
                "message", message
        );
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    /**
     * @param data    The data to be sent in the response
     * @param message The message to be sent in the response
     * @return ResponseEntity with the data, message, and status
     */
    public static ResponseEntity<?> sendError(Object data, String message) {
        var responseBody = Map.of(
                "data", data,
                "message", message
        );
        return new ResponseEntity<>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
