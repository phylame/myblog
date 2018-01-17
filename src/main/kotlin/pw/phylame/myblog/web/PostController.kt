package pw.phylame.myblog.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import pw.phylame.myblog.domain.CategoryRepository
import pw.phylame.myblog.domain.CommentRepository
import pw.phylame.myblog.domain.PostRepository

@Controller
@RequestMapping("post")
class PostController : BaseController() {
    @field:Autowired
    lateinit var postRepository: PostRepository

    @field:Autowired
    lateinit var categoryRepository: CategoryRepository

    @field:Autowired
    lateinit var commentRepository: CommentRepository

    @GetMapping(value = ["", "/"])
    fun listPosts(model: Model, @RequestParam(defaultValue = "-1") category: Int, @RequestParam(defaultValue = "-1") tag: Int): String {
        val posts = when {
            category != -1 -> postRepository.findAllByCategoryId(category, null)
            tag != -1 -> postRepository.findAllByTagsId(tag, null)
            else -> postRepository.findAll(null as Pageable?)
        }
        model.addAttribute("activeNav", "post")
        model.addAttribute("posts", posts)
        model.addAttribute("categories", categoryRepository.findAll())
        return "post/list"
    }

    @GetMapping("{postId}")
    fun viewPost(model: Model, @PathVariable postId: Int): String {
        model.addAttribute("activeNav", "post")
        model.addAttribute("post", postRepository.findOne(postId))
        return "post/view"
    }
}
