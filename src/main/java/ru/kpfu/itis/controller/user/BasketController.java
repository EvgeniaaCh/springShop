package ru.kpfu.itis.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.form.change.OrderChangeForm;
import ru.kpfu.itis.form.OrderForm;
import ru.kpfu.itis.model.Order;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.OrderStatus;
import ru.kpfu.itis.service.OrderService;
import ru.kpfu.itis.service.ProductInOrderService;

@Controller
@RequestMapping(value = "basket")
public class BasketController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductInOrderService productInOrderService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String basket(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Order basket = orderService.getByUserAndOrderStatus(user, OrderStatus.DIALED);
        model.addAttribute("order", basket);
        model.addAttribute("productsInOrder", productInOrderService.getAllByOrder(basket));
        model.addAttribute("orderForm", new OrderForm());
        return "basketList";
    }

    @Transactional
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String basket(@ModelAttribute("orderForm") OrderForm form) {
        Order order = orderService.getById(form.getOrder_id());
        OrderChangeForm orderchangeForm = new OrderChangeForm();
        orderchangeForm.setOrder(order);
        orderchangeForm.setOrderStatus(OrderStatus.FORMAD);
        orderService.change(orderchangeForm);
        return "redirect:/orders/";
    }

    @Transactional
    @RequestMapping(value = "/minus", method = RequestMethod.GET)
    public @ResponseBody int minusCount(@RequestParam int id, @RequestParam int count) {
        return orderService.getResponseAboutMinusCount(id, count);
    }

    @Transactional
    @RequestMapping(value = "/plus", method = RequestMethod.GET)
    public @ResponseBody int plusCount(@RequestParam int id, @RequestParam int count) {
        return orderService.getResponseAboutPlusCount(id, count);
    }
}
