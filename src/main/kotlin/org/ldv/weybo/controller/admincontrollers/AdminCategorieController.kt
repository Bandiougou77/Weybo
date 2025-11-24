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

    @GetMapping("")
    fun list(model: Model): String {
        model.addAttribute("categories", categorieDAO.findAll())
        return "pagesAdmin/categories/categories-list"
    }

    @GetMapping("/create")
    fun create(model: Model): String {
        model.addAttribute("categorie", Categorie())
        return "pagesAdmin/articles/articles-form"
    }

    @PostMapping("/save")
    fun save(@ModelAttribute categorie: Categorie): String {
        categorieDAO.save(categorie)
        return "redirect:/admin/categories"
    }

    @GetMapping("/edit/{id}")
    fun edit(@PathVariable id: Long, model: Model): String {
        val cat = categorieDAO.findById(id).orElse(null)
        model.addAttribute("categorie", cat)
        return "pagesAdmin/categories-form"
    }

    @GetMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): String {
        categorieDAO.deleteById(id)
        return "redirect:/admin/categories"
    }
}





