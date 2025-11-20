package org.ldv.weybo.controller.admincontrollers

import org.ldv.weybo.model.dao.ArticleDAO
import org.ldv.weybo.model.dao.CategorieDAO
import org.ldv.weybo.model.entity.Article
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@Controller
@RequestMapping("/admin/articles")
class AdminArticleController(
    private val articleDAO: ArticleDAO,
    private val categorieDAO: CategorieDAO
) {

    // LISTE
    @GetMapping("")
    fun list(model: Model): String {
        model.addAttribute("articles", articleDAO.findAll())
        return "pagesAdmin/articles/articles-list"
    }

    // FORMULAIRE CREATION
    @GetMapping("/create")
    fun showCreateForm(model: Model): String {
        model.addAttribute("article", Article())
        model.addAttribute("categories", categorieDAO.findAll())
        return "pagesAdmin/articles/articles-form"
    }

    // FORMULAIRE EDITION
    @GetMapping("/edit/{id}")
    fun showEditForm(@PathVariable id: Long, model: Model): String {
        val article = articleDAO.findById(id).orElse(null)
            ?: return "redirect:/admin/articles"

        model.addAttribute("article", article)
        model.addAttribute("categories", categorieDAO.findAll())
        return "pagesAdmin/articles/articles-form"
    }

    // SAUVEGARDE (create + update)
    @PostMapping("/save")
    fun save(
        @RequestParam(required = false) id: Long?,
        @RequestParam nom: String,
        @RequestParam description: String,
        @RequestParam prix: Double,
        @RequestParam stock: Int,
        @RequestParam image: String,
        @RequestParam categorieId: Long
    ): String {

        val categorie = categorieDAO.findById(categorieId).orElse(null)

        // UPDATE
        val article = if (id != null) {
            val existing = articleDAO.findById(id).orElse(Article())
            existing.apply {
                this.nom = nom
                this.description = description
                this.prix = prix              // ⭐ CORRECT : Double → Double
                this.stock = stock
                this.image = image
                this.categorie = categorie
            }
        } else {
            // CREATE
            Article(
                nom = nom,
                description = description,
                prix = prix,                   // ⭐ CORRECT : pas de BigDecimal
                stock = stock,
                image = image,
                categorie = categorie
            )
        }

        articleDAO.save(article)
        return "redirect:/admin/articles"
    }

    // DELETE
    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): String {
        if (articleDAO.existsById(id)) {
            articleDAO.deleteById(id)
        }
        return "redirect:/admin/articles"
    }
}



