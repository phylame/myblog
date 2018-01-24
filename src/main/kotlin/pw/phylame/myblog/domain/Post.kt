package pw.phylame.myblog.domain

import org.hibernate.validator.constraints.NotEmpty
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
class Post {
    @get:[Id GeneratedValue]
    var id: Int = 0

    @get:[NotEmpty Column(unique = true, length = 128)]
    lateinit var title: String

    @get:[ManyToOne JoinColumn(name = "category_id")]
    lateinit var category: Category

    @get:[NotEmpty ManyToMany]
    lateinit var tags: MutableSet<Tag>

    @get:[Column(nullable = false)]
    var createTime: LocalDateTime = LocalDateTime.now()

    var updateTime: LocalDateTime? = null

    @get:[NotEmpty Column(length = 1024)]
    lateinit var intro: String

    @get:[NotEmpty Column(length = 1024)]
    lateinit var contentUrl: String

    @get:[OneToMany(mappedBy = "post")]
    var comments: MutableList<Comment> = mutableListOf()

    var rank: Int = 0

    @get:[NotNull OneToOne(mappedBy = "post")]
    var stat: PostStat = PostStat()
}

interface PostRepository : JpaRepository<Post, Int> {
    fun findAllByCategoryId(category: Int, pageable: Pageable?): Page<Post>

    fun findAllByTagsId(tag: Int, pageable: Pageable?): Page<Post>
}

@Entity
class PostStat {
    @get:[Id GeneratedValue]
    var id: Int = 0

    @get:[OneToOne JoinColumn(name = "post_id")]
    lateinit var post: Post

    var viewCount: Int = 0

    var voteCount: Int = 0
}
