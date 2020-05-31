package org.example.articles

class ArticleUsecase(private val repository: IArticleRepository): IArticleUsecase {
    override fun getArticles(): List<Article> {
        return repository.getArticles()
    }
}
