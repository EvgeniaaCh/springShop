package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.form.UserRegistrationForm;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.util.validator.InputValidator;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    private InputValidator InputValidator = new InputValidator();

    @RequestMapping(value = "/")
    public String getIndexPage() {
        return "home";
    }

    @RequestMapping(value = "/login")
    public String getLoginPage(@RequestParam(value = "error", required = false) Boolean error, Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", error);
        }
        return "loginForm";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistrationPage(Model model) {
        model.addAttribute("userForm", new UserRegistrationForm());
        model.addAttribute("error", "");
        return "registrationForm";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("userForm") UserRegistrationForm form, BindingResult result, Model model) {
        InputValidator.validate(form, result);
        if (userService.getOneByUsername(form.getUsername())!= null){
            model.addAttribute("userForm", form);
            model.addAttribute("error", "Имя занято");
        }
        if (result.hasErrors()) {
            return "registrationForm";
        } else {
            userService.save(form);
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/activate", method = RequestMethod.GET)
    public String activate(@ModelAttribute("tokenUuid") String token) {
        if (userService.activatedUser(token)) {
            return "regMessage";
        }
        return null;
    }

}