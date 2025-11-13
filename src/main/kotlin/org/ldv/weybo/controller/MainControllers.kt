package org.ldv.weybo.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/Weybo")
@Controller
class MainController {

    @GetMapping("/","/home")
    fun home() = "pagesVisiteur/index"

    @GetMapping("/shop")
    fun shop() = "pagesVisiteur/shop"

    @GetMapping("/cart")
    fun cart() = "pagesVisiteur/cart"

    @GetMapping("/login")
    fun login() = "pagesVisiteur/login"

    @GetMapping("/register")
    fun register() = "pagesVisiteur/register"
}


