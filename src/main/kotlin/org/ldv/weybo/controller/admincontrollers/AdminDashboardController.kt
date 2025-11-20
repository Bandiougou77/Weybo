package org.ldv.weybo.controller.admincontrollers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin")
class AdminDashboardController {

    @GetMapping("")
    fun dashboard(): String {
        return "pagesAdmin/admin-dashboard"
    }
}
