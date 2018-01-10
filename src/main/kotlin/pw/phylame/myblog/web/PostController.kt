package pw.phylame.myblog.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import pw.phylame.myblog.data.ArticleRepository
import pw.phylame.myblog.data.CategoryRepository
import javax.servlet.http.HttpServletRequest

@Controller
@RequestMapping("post")
class PostController {
    @field:Autowired
    lateinit var request: HttpServletRequest

    @field:Autowired
    lateinit var articleRepository: ArticleRepository

    @field:Autowired
    lateinit var categoryRepository: CategoryRepository

    @GetMapping(value = ["", "/"])
    fun listPosts(model: Model, @RequestParam(defaultValue = "-1") category: Int): String {
        model.addAttribute("posts", articleRepository.findAll())
        model.addAttribute("categories", categoryRepository.findAll())
        return "post/list"
    }
}
