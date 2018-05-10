package ru.kpfu.itis.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.form.change.OrderChangeForm;
import ru.kpfu.itis.form.OrderForm;
import ru.kpfu.itis.model.Order;
import ru.kpfu.itis.model.enums.OrderStatus;
import ru.kpfu.itis.service.OrderService;
import ru.kpfu.itis.service.ProductInOrderService;
import ru.kpfu.itis.service.UserService;

@Controller
@RequestMapping(value = "admin/order")
public class OrderController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductInOrderService productInOrderService;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String allOrders(Model model) {
        model.addAttribute("orderForm", new OrderForm());
        model.addAttribute("orders", orderService.getAll());
        return "orderList";
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public String allOrders(@ModelAttribute("orderForm") OrderForm form) {
        Order order = orderService.getById(form.getOrder_id());
        OrderStatus orderStatus = OrderStatus.valueOf(form.getOrderStatus());
        OrderChangeForm orderChangeForm = new OrderChangeForm();
        orderChangeForm.setOrder(order);
        orderChangeForm.setOrderStatus(orderStatus);
        orderService.change(orderChangeForm);
        return "redirect:/admin/order/all";
    }
}
