package org.ldv.weybo.model.dao


import org.ldv.weybo.model.entity.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleDAO : JpaRepository<Article, Long>

