package org.ldv.weybo.controller.admincontrollers

import org.ldv.weybo.model.dao.ArticleDAO
import org.ldv.weybo.model.dao.CategorieDAO
import org.ldv.weybo.model.entity.Article
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/admin/articles")
class AdminArticleController(
    private val articleDAO: ArticleDAO,
    private val categorieDAO: CategorieDAO
) {

    @GetMapping("")
    fun list(model: Model): String {
        model.addAttribute("articles", articleDAO.findAll())
        return "pagesAdmin/articles/articles-list"
    }

    @GetMapping("/create")
    fun create(model: Model): String {
        model.addAttribute("article", Article())
        model.addAttribute("categories", categorieDAO.findAll())
        return "pagesAdmin/articles/articles-form"
    }

    @PostMapping("/save")
    fun save(@ModelAttribute article: Article): String {
        articleDAO.save(article)
        return "redirect:/admin/articles"
    }

    @GetMapping("/edit/{id}")
    fun edit(@PathVariable id: Long, model: Model): String {
        val article = articleDAO.findById(id).orElse(null)
        model.addAttribute("article", article)
        model.addAttribute("categories", categorieDAO.findAll())
        return "pagesAdmin/articles/articles-form"
    }

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): String {
        articleDAO.deleteById(id)
        return "redirect:/admin/articles"
    }
}



