package pw.phylame.myblog.web

import org.springframework.web.bind.annotation.ModelAttribute

abstract class BaseController {
    @ModelAttribute("navItems")
    fun navItems(): List<String> {
        return listOf("home", "app", "post", "about")
    }
}
