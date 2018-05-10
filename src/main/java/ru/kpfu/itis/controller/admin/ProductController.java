package ru.kpfu.itis.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.form.add.AddProductForm;
import ru.kpfu.itis.form.ProductForm;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.service.ProductService;
import ru.kpfu.itis.service.AccountingService;
import ru.kpfu.itis.service.StorageService;
import ru.kpfu.itis.util.validator.ValidatorAddProductForm;

@Controller
@RequestMapping(value = "admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private AccountingService accountingService;

    private ValidatorAddProductForm validatorAddProductForm = new ValidatorAddProductForm();

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String catalog(Model model) {
        model.addAttribute("products", productService.getAll());
        model.addAttribute("changeProduct", new ProductForm());
        return "productList";
    }

    @RequestMapping(value = "/{id}")
    public String productPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "product";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addProductPage(Model model){
        model.addAttribute("productForm", new AddProductForm());
        return "addProductForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("productForm") AddProductForm form, BindingResult result){
        validatorAddProductForm.validate(form, result);

        if (result.hasErrors()) {
            return "addProductForm";
        } else {
            productService.save(form);
            return "redirect:/admin/product/all";
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public String mod(@ModelAttribute("changeProduct") ProductForm form){
        Product product = productService.getById(form.getId());
        form.setProduct(product);
        productService.change(form);
        return "redirect:/admin/product/all";
    }
}
