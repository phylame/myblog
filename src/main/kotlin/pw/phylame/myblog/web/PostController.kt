package pw.phylame.myblog.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import pw.phylame.myblog.data.ArticleRepository
import pw.phylame.myblog.data.CategoryRepository
import pw.phylame.myblog.data.CommentRepository

@Controller
@RequestMapping("post")
class PostController : BaseController() {
    @field:Autowired
    lateinit var articleRepository: ArticleRepository

    @field:Autowired
    lateinit var categoryRepository: CategoryRepository

    @field:Autowired
    lateinit var commentRepository: CommentRepository

    @GetMapping(value = ["", "/"])
    fun listPosts(model: Model, @RequestParam(defaultValue = "-1") category: Int, @RequestParam(defaultValue = "-1") tag: Int): String {
        val articles = when {
            category != -1 -> articleRepository.findAllByCategoryId(category, null)
            tag != -1 -> articleRepository.findAllByTagsId(tag, null)
            else -> articleRepository.findAll(null as Pageable?)
        }
        model.addAttribute("activeNav", "post")
        model.addAttribute("posts", articles)
        model.addAttribute("categories", categoryRepository.findAll())
        return "post/list"
    }

    @GetMapping("{postId}")
    fun viewPost(model: Model, @PathVariable postId: Int): String {
        model.addAttribute("activeNav", "post")
        model.addAttribute("post", articleRepository.findOne(postId))
        return "post/view"
    }
}
