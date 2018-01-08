package pw.phylame.myblog

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class BlogApplication

fun main(args: Array<String>) {
    SpringApplication.run(BlogApplication::class.java, *args)
}
