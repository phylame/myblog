package pw.phylame.myblog.domain

import org.hibernate.validator.constraints.NotEmpty
import org.springframework.data.jpa.repository.JpaRepository
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Min

@Entity
class Tag : Serializable {
    @get:[Id GeneratedValue]
    var id: Int = 0

    @get:[NotEmpty Column(unique = true, length = 32)]
    lateinit var name: String

    @get:[Column(nullable = false)]
    var createTime: LocalDateTime = LocalDateTime.now()

    @get:[Min(0)]
    var rank: Int = 0
}

interface TagRepository : JpaRepository<Tag, Int> {
    fun findOneByName(name: String): Tag?
}
