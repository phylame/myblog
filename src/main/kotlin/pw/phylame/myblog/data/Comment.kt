package pw.phylame.myblog.data

import org.hibernate.validator.constraints.NotEmpty
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Comment(
        @get:[Id GeneratedValue]
        var id: Int = 0,

        @get:Column(length = 128)
        var name: String = "",

        @get:Column(length = 64)
        var email: String = "",

        @get:NotNull
        var creation: LocalDateTime = LocalDateTime.now(),

        @get:[NotNull ManyToOne JoinColumn(name = "article_id")]
        var article: Article = Article(),

        @get:[NotEmpty Column(length = 1024)]
        var content: String = ""
) : Serializable

interface CommentRepository : PagingAndSortingRepository<Comment, Int> {
    fun findAllByArticleId(articleId: Int, pageable: Pageable?): Page<Comment>
}
