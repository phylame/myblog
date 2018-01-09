package pw.phylame.myblog.data

import org.hibernate.validator.constraints.NotEmpty
import org.springframework.data.repository.PagingAndSortingRepository
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotNull

@Entity
data class Category(
        @get:[Id GeneratedValue]
        var id: Int = 0,

        @get:[NotEmpty Column(unique = true, length = 32)]
        var name: String = "",

        @get:NotNull
        var creation: LocalDateTime = LocalDateTime.now()
) : Serializable

interface CategoryRepository : PagingAndSortingRepository<Category, Int>
