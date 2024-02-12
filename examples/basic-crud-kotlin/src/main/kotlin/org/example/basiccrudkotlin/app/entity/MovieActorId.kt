package org.example.basiccrudkotlin.app.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.Hibernate
import java.io.Serializable
import java.util.*

@Embeddable
open class MovieActorId : Serializable {
    @Column(name = "MOVIE_ID", nullable = false)
    open var movieId: Int? = null

    @Column(name = "ACTOR_ID", nullable = false)
    open var actorId: Int? = null
    override fun hashCode(): Int = Objects.hash(movieId, actorId)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false

        other as MovieActorId

        return movieId == other.movieId &&
                actorId == other.actorId
    }

    companion object {
        private const val serialVersionUID = -7721510229263762687L
    }
}