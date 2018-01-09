package pw.phylame.myblog.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import pw.phylame.myblog.data.ArticleRepository
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("post")
class PostController {
    @field:Autowired
    lateinit var request: HttpServletRequest

    @field:Autowired
    lateinit var articleRepository: ArticleRepository

    @GetMapping(value = ["", "/"])
    fun listBlogs(model: Model): String {
        model.addAttribute("posts", articleRepository.findAll())
        return "blog"
    }
}
