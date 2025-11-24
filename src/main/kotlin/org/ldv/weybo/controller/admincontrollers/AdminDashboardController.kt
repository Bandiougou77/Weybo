package org.ldv.weybo.controller.admincontrollers


import org.ldv.weybo.model.dao.ArticleDAO
import org.ldv.weybo.model.dao.CategorieDAO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/admin")
class AdminDashboardController(
    private val articleDAO: ArticleDAO,
    private val categorieDAO: CategorieDAO
) {

    // ========== DASHBOARD ==========
    @GetMapping("/dashboard")
    fun dashboard(model: Model): String {
        model.addAttribute("nbArticles", articleDAO.count())
        model.addAttribute("nbCategories", categorieDAO.count())
        return "pagesAdmin/dashboard"
    }





}

