package org.example.basiccrudkotlin.app.entity

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "REVIEWS")
open class Review {
    @Id
    @Column(name = "ID", nullable = false)
    open var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "MOVIE_ID", nullable = false)
    open var movie: Movie? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "USER_ID", nullable = false)
    open var user: User? = null

    @Column(name = "RATING", precision = 3, scale = 2)
    open var rating: BigDecimal? = null

    @Column(name = "COMMENT")
    open var comment: String? = null

    @Column(name = "REVIEW_DATE", nullable = false)
    open var reviewDate: LocalDate? = null
}