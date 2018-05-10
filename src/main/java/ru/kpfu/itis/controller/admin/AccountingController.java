package ru.kpfu.itis.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.form.add.AddAccountingForm;
import ru.kpfu.itis.form.AccountingForm;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.Storage;
import ru.kpfu.itis.service.ProductService;
import ru.kpfu.itis.service.AccountingService;
import ru.kpfu.itis.service.StorageService;

@Controller
@RequestMapping(value = "admin/accounting")
public class AccountingController {

    @Autowired
    private AccountingService accountingService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addAccounting(Model model){
        model.addAttribute("accountingForm", new AccountingForm());
        model.addAttribute("products", productService.getAll());
        model.addAttribute("storages", storageService.getAll());
        return "addAccountingForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAccounting(@ModelAttribute("accountingForm") AccountingForm form){
        Product product = productService.getById(form.getProduct());
        Storage storage = storageService.getById(form.getStorage());
        AddAccountingForm addAccountingForm = new AddAccountingForm();
        addAccountingForm.setProduct(product);
        addAccountingForm.setStorage(storage);
        addAccountingForm.setCount(form.getCount());
        accountingService.save(addAccountingForm);
        return "redirect:/admin/storage/all";
    }
}
