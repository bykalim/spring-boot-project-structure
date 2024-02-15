package org.example.advancedcrud.app.projection.user;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
public interface UserProjection {

    interface DefaultView {
        int id();
        String getUsername();
    }
}