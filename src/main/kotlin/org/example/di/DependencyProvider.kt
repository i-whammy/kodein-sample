package org.example.di

import org.example.articles.ArticleRepository
import org.example.articles.ArticleUsecase
import org.example.articles.IArticleRepository
import org.example.articles.IArticleUsecase
import org.example.config.Configuration
import org.example.config.MobileConfiguration
import org.example.config.WebConfiguration
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class DependencyProvider {
    companion object {
        fun provideArticleDependency() = articleDependency
        fun provideConfigurationDependency() = configurationDependency
    }
}

private val articleDependency = Kodein.Module(name="dependency") {
    bind<IArticleRepository>() with singleton { ArticleRepository() }
    bind<IArticleUsecase>() with singleton { ArticleUsecase(instance()) }
}

private val configurationDependency = Kodein.Module(name="configurationDependency") {
    bind<Configuration>(tag="mobile") with singleton { MobileConfiguration() }
    bind<Configuration>(tag="web") with singleton { WebConfiguration() }
}