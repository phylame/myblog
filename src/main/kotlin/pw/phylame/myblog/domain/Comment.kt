package pw.phylame.myblog.domain

import org.hibernate.validator.constraints.NotEmpty
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Comment : Serializable {
    @get:[Id GeneratedValue]
    var id: Int = 0

    @get:[Column(length = 64)]
    var name: String? = ""

    @get:[Column(length = 128)]
    var email: String? = ""

    @get:[ManyToOne JoinColumn(name = "post_id", nullable = false)]
    lateinit var post: Post

    @get:[ManyToOne JoinColumn(name = "origin_id")]
    var origin: Comment? = null

    @get:[OneToMany(mappedBy = "origin")]
    var replies: MutableList<Comment> = mutableListOf()

    @get:[Column(nullable = false)]
    var createTime: LocalDateTime = LocalDateTime.now()

    @get:[NotEmpty Column(length = 2048)]
    lateinit var content: String
}

interface CommentRepository : JpaRepository<Comment, Int> {
    fun findAllByPostId(articleId: Int, pageable: Pageable?): Page<Comment>

    fun findAllByOriginId(originId: Int, pageable: Pageable?): Page<Column>
}
