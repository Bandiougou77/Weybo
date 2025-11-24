package org.ldv.weybo.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.security.core.Authentication


@Controller
  // <<< IMPORTANT : ne pas mettre /weybo ici
class MainController {

    @GetMapping("/", "/home")
    fun home(): String {
        return "pagesVisiteur/index"
    }

    @GetMapping("/shop")
    fun shop(): String = "pagesVisiteur/shop"

    @GetMapping("/cart")
    fun cart(): String = "pagesVisiteur/cart"



    @GetMapping("/register")
    fun register(): String = "pagesVisiteur/register"


    // --- PAGE LOGIN — gérée par Spring Security ---
    @GetMapping("/login")
    fun login(
        @RequestParam(value = "error", required = false) error: Boolean?,
        model: Model
    ): String {

        if (error == true) {
            model.addAttribute("error", "Identifiants incorrects.")
        }

        return "pagesVisiteur/login"
    }


    // --- REDIRECTION APRÈS LOGIN ---
    @GetMapping("/profil")
    fun profil(auth: Authentication): String {

        val roles = auth.authorities.map { it.authority }

        return if ("ROLE_ADMIN" in roles) {
            "redirect:/admin/dashboard"
        } else {
            "pagesClient/profile"
        }
    }
}





