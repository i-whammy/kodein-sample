package org.example

import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.example.articles.Article
import org.example.articles.ArticleUsecase
import org.example.articles.IArticleUsecase
import org.example.config.Configuration
import org.example.di.DependencyProvider
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import org.kodein.di.newInstance

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, port = 8080) {
        val kodein = Kodein {
            import(DependencyProvider.provideConfigurationDependency())
            import(DependencyProvider.provideArticleDependency())
        }
        val configuration by kodein.instance<Configuration>(tag = args[0])
        configuration.load()

        install(ContentNegotiation) {
            jackson {  }
        }
        routing {
            get("/articles") {
                val usecase by kodein.newInstance<IArticleUsecase> { ArticleUsecase(instance()) }
                call.respond(usecase.getArticles().first())
            }
        }
    }
    server.start(wait = true)
}
