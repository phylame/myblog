package pw.phylame.myblog.domain

import org.hibernate.validator.constraints.NotEmpty
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import java.time.LocalDateTime
import javax.persistence.*

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

    var viewCount: Int = 0

    var voteCount: Int = 0
}

interface PostRepository : PagingAndSortingRepository<Post, Int> {
    fun findAllByCategoryId(category: Int, pageable: Pageable?): Page<Post>

    fun findAllByTagsId(tag: Int, pageable: Pageable?): Page<Post>
}
