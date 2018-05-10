package ru.kpfu.itis.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.form.OrderForm;
import ru.kpfu.itis.model.Order;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.OrderStatus;
import ru.kpfu.itis.service.OrderService;
import ru.kpfu.itis.service.ProductInOrderService;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(value = "orders")
public class OrderUserController {

    @Autowired
    private ProductInOrderService productInOrderService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String allOrders(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrderStatus> orderStatus = new LinkedList<>();
        orderStatus.add(OrderStatus.COMPLETED);
        orderStatus.add(OrderStatus.SEARS);
        orderStatus.add(OrderStatus.DIALED);
        orderStatus.add(OrderStatus.FORMAD);
        List<Order> orders = orderService.getForList(user.getId(), orderStatus);
        model.addAttribute("orderForm", new OrderForm());
        model.addAttribute("orders", orders);
        model.addAttribute("productInOrders", productInOrderService.getAllByOrderIn(orders));
        return "orderList";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id") Long id, Model model) {
        Order order = orderService.getById(id);
        order.setOrderStatus(OrderStatus.DELETE);
        orderService.save(order);
        model.addAttribute("id", id);
        return "redirect:/orders/";
    }
}
