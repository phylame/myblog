package pw.phylame.myblog.domain

import org.hibernate.validator.constraints.NotEmpty
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Min

@Entity
class Category : Serializable {
    @get:[Id GeneratedValue]
    var id: Int = 0

    @get:[NotEmpty Column(unique = true, length = 32)]
    lateinit var name: String

    @get:[Column(nullable = false)]
    var createTime: LocalDateTime = LocalDateTime.now()

    @get:[ManyToOne]
    var parent: Category? = null

    @get:[OneToMany(mappedBy = "parent")]
    var children: MutableSet<Category> = mutableSetOf()

    @get:[Min(0)]
    var rank: Int = 0
}

interface CategoryRepository : PagingAndSortingRepository<Category, Int> {
    fun findOneByName(name: String): Category?

    fun findAllByParentId(parentId: Int, pageable: Pageable?): Page<Category>
}
