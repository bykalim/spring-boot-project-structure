package org.example.basiccrudkotlin.app.entity

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "MOVIE_ACTORS")
open class MovieActor {
    @EmbeddedId
    open var id: MovieActorId? = null

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    open var movie: Movie? = null

    @MapsId("actorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ACTOR_ID", nullable = false)
    open var actor: Actor? = null
}