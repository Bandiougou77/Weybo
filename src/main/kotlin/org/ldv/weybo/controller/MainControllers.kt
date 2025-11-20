package org.ldv.weybo.controller

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/weybo")
open class MainController {

    // --- PAGES VISITEUR --- //

    @GetMapping("/home")
    open fun home() : String {
        return "pagesVisiteur/index"
    }

    @GetMapping("/shop")
    open fun shop() : String {
        return "pagesVisiteur/shop"
    }

    @GetMapping("/cart")
    open fun cart() : String {
        return "pagesVisiteur/cart"
    }

    @GetMapping("/register")
    open fun register() : String {
        return "pagesVisiteur/register"
    }


    // --- PAGE LOGIN GÉRÉE PAR SPRING SECURITY --- //

    @GetMapping("/login")
    open fun login(
        @RequestParam(name = "error", required = false) error: Boolean?,
        model: Model
    ) : String {

        if (error == true) {
            model.addAttribute("error", "Identifiants incorrects.")
        }

        return "pagesVisiteur/login"
    }


    // --- REDIRECTION APRÈS LOGIN --- //

    @GetMapping("/profil")
    open fun profil(authentication: Authentication) : String {

        val roles = authentication.authorities.map { it.authority }

        return if ("ROLE_ADMIN" in roles) {
            "redirect:/admin/dashboard"
        } else {
            "pagesClient/profile"
        }
    }
}



