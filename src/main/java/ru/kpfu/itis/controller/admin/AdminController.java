package ru.kpfu.itis.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "admin")
public class AdminController {

    @RequestMapping(value = "/main")
    public String main() {
        return "admin";
    }
}
