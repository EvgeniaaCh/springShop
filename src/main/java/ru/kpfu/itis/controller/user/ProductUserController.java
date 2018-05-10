package ru.kpfu.itis.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.form.OrderForm;
import ru.kpfu.itis.form.ProductInOrderForm;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.ProductInOrder;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.service.*;


@Controller
@RequestMapping(value = "product")
public class ProductUserController {

    @Autowired
    private ProductService productService;
    @Autowired
    private AccountingService accountingService;
    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String catalog(@RequestParam(value = "name", required = false) String productname, Model model) {

        if (productname != null) {
            model.addAttribute("productForm", new OrderForm());
            model.addAttribute("accounting", accountingService.getAllByProductName(productname));
            return "catalog";
        }
        model.addAttribute("productForm", new OrderForm());
        model.addAttribute("accounting", accountingService.getAll());
        model.addAttribute("error", "");
        return "catalog";
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String catalog(@ModelAttribute("productForm") ProductInOrderForm form, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.isis_confirm()){
            model.addAttribute("error", "Пользователь не активен");
            model.addAttribute("accounting", accountingService.getAll());
            return "catalog";
        }
        Product product = productService.getById(form.getProduct_id());
        ProductInOrder productInOrder = new ProductInOrder();
        productInOrder.setProduct(product);
        orderService.save(user, productInOrder);
        return "redirect:/basket/";
    }

    @RequestMapping(value = "/{id}")
    public String productPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "product";
    }
}
