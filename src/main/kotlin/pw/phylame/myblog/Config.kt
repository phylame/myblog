package pw.phylame.myblog

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter


@Configuration
class WebMvcConfig : WebMvcConfigurerAdapter() {
    override fun configureViewResolvers(registry: ViewResolverRegistry) {
    }
}

@Configuration
class GlobalRepositoryRestConfigurer : RepositoryRestConfigurerAdapter() {
    @field:Autowired
    private lateinit var env: Environment

    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration) {
        if ("dev" in env.activeProfiles) {
            config.corsRegistry
                    .addMapping("/api/*")
                    .allowedOrigins("*")
                    .allowedHeaders("*")
                    .allowedMethods("*")
        }
    }

}
