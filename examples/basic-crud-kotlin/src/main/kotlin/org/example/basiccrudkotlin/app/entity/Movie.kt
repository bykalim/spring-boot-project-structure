package org.example.basiccrudkotlin.app.entity

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDate

@Entity
@Table(name = "MOVIES")
open class Movie {
    @Id
    @Column(name = "ID", nullable = false)
    open var id: Long? = null

    @Column(name = "TITLE", nullable = false)
    open var title: String? = null

    @Column(name = "DESCRIPTION")
    open var description: String? = null

    @Column(name = "RELEASE_DATE")
    open var releaseDate: LocalDate? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "GENRE_ID")
    open var genre: Genre? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "DIRECTOR_ID")
    open var director: Director? = null
}