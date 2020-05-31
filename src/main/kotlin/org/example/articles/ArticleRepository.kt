package org.example.articles

class ArticleRepository: IArticleRepository {
    override fun getArticles(): List<Article> {
        return listOf(Article("This is the first article for me!"))
    }
}