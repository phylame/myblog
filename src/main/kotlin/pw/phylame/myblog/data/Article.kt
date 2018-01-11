package pw.phylame.myblog.data

import org.hibernate.annotations.ColumnDefault
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Article(
        @get:[Id GeneratedValue]
        var id: Int = 0,

        @get:[NotEmpty Column(unique = true, length = 256)]
        var title: String = "",

//        @get:[NotEmpty Column(length = 1024)]
//        var intro: String = "",

        @get:[NotNull ManyToOne JoinColumn(name = "category_id")]
        var category: Category = Category(),

        @get:NotNull
        var creation: LocalDateTime = LocalDateTime.now(),

        @get:[NotEmpty ManyToMany]
        var tags: List<Tag> = emptyList(),

        @get:[NotEmpty Column(length = 512)]
        var contentUrl: String = "",

        @get:OneToMany(mappedBy = "article")
        var comments: List<Comment> = emptyList(),

        @get:ColumnDefault("0")
        var readCount: Int = 0,

        @get:ColumnDefault("0")
        var starCount: Int = 0
) : Serializable

interface ArticleRepository : PagingAndSortingRepository<Article, Int> {
    fun findAllByCategoryId(categoryId: Int, pageable: Pageable?): Page<Article>

    fun findAllByTagsId(tagId: Int, pageable: Pageable?): Page<Article>
}
