package org.ldv.weybo.controller.admincontrollers

import org.ldv.weybo.model.dao.CategorieDAO
import org.ldv.weybo.model.entity.Categorie
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/admin/categories")
class AdminCategorieController(
    private val categorieDAO: CategorieDAO
) {

    // LISTE
    @GetMapping("")
    fun list(model: Model): String {
        model.addAttribute("categories", categorieDAO.findAll())
        return "pagesAdmin/categories/categories-list"
    }

    // FORM CREATION
    @GetMapping("/create")
    fun showCreateForm(model: Model): String {
        model.addAttribute("categorie", Categorie())
        return "pagesAdmin/categories/categories-form"
    }

    // FORM EDITION
    @GetMapping("/edit/{id}")
    fun showEditForm(@PathVariable id: Long, model: Model): String {
        val categorie = categorieDAO.findById(id).orElse(null)
            ?: return "redirect:/admin/categories"

        model.addAttribute("categorie", categorie)
        return "pagesAdmin/categories/categories-form"
    }

    // SAVE
    @PostMapping("/save")
    fun save(
        @RequestParam(required = false) id: Long?,
        @RequestParam nom: String,
        @RequestParam description: String
    ): String {

        val categorie = if (id != null) {
            val existing = categorieDAO.findById(id).orElse(null) ?: Categorie()
            existing.copy(
                nom = nom,
                description = description
            )
        } else {
            Categorie(
                nom = nom,
                description = description
            )
        }

        categorieDAO.save(categorie)
        return "redirect:/admin/categories"
    }

    // DELETE
    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): String {
        if (categorieDAO.existsById(id)) {
            categorieDAO.deleteById(id)
        }
        return "redirect:/admin/categories"
    }
}





