package org.example.advancedcrud.app.entity;

import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class MovieActorId implements Serializable {
    private static final long serialVersionUID = 6615578765469384867L;
    @Column(name = "movie_id", nullable = false)
    private Integer movieId;

    @Column(name = "actor_id", nullable = false)
    private Integer actorId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MovieActorId entity = (MovieActorId) o;
        return Objects.equals(this.actorId, entity.actorId) &&
                Objects.equals(this.movieId, entity.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, movieId);
    }

}