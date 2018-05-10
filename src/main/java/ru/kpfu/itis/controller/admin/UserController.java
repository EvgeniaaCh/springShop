package ru.kpfu.itis.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.form.change.UserChangeForm;
import ru.kpfu.itis.form.UserForm;
import ru.kpfu.itis.model.Order;
import ru.kpfu.itis.model.ProductInOrder;
import ru.kpfu.itis.model.Token;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.UserRole;
import ru.kpfu.itis.repository.TokenRepository;
import ru.kpfu.itis.service.OrderService;
import ru.kpfu.itis.service.ProductInOrderService;
import ru.kpfu.itis.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "admin/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductInOrderService productInOrderService;
    @Autowired
    private TokenRepository tokenRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String allUsers(Model model) {
        model.addAttribute("userChangeForm", new UserForm());
        model.addAttribute("users", userService.getAll());
        return "userList";
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public String allUsers(@ModelAttribute("userChangeForm") UserForm form) {
        User user = userService.getById(form.getUser_id());
        UserRole userRole = UserRole.valueOf(form.getRole());
        UserChangeForm changeUserForm = new UserChangeForm();
        changeUserForm.setUser(user);
        changeUserForm.setRole(userRole);
        changeUserForm.setis_confirm(form.getIs_confirm());
        userService.changeUser(changeUserForm);
        return "redirect:/admin/user/all";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id", required = true) Long id, Model model) {
        User user = userService.getById(id);
        Token token = tokenRepository.findOneByUser(user);
        tokenRepository.delete(token);
        List<Order> orders = orderService.getByUser(user);
        List<ProductInOrder> productInOrders = productInOrderService.getAllByOrderIn(orders);
        productInOrderService.delete(productInOrders);
        orderService.delete(orders);
        userService.delete(user);
        model.addAttribute("id", id);
        return "redirect:/admin/user/all";
    }
}
