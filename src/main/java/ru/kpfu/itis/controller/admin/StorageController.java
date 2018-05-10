package ru.kpfu.itis.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.form.add.AddCountForm;
import ru.kpfu.itis.form.add.AddStorageForm;
import ru.kpfu.itis.form.CountForm;
import ru.kpfu.itis.model.Accounting;
import ru.kpfu.itis.service.ProductService;
import ru.kpfu.itis.service.AccountingService;
import ru.kpfu.itis.service.StorageService;
import ru.kpfu.itis.util.validator.ValidatorAddStorageForm;

@Controller
@RequestMapping(value = "admin/storage")
public class StorageController {

    @Autowired
    private ProductService productService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private AccountingService accountingService;

    private ValidatorAddStorageForm validatorAddStorageForm = new ValidatorAddStorageForm();

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String storages(Model model) {
        model.addAttribute("storages", storageService.getAll());
        return "storageList";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id", required=true) Long id,
                         Model model) {
        for (Accounting accounting: accountingService.getAllByStorageId(id)) {
            accountingService.delete(accounting.getId());
        }
        storageService.delete(id);
        model.addAttribute("id", id);
        return "redirect:/admin/storage/all";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String productPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("storage", storageService.getById(id));
        model.addAttribute("accounting", accountingService.getAllByStorageId(id));
        model.addAttribute("count", accountingService.getAllCountOnStorage(accountingService.getAllByStorageId(id)));
        model.addAttribute("products", productService.getByIdIn(accountingService.getIdsProducts(accountingService.getAllByStorageId(id))));
        model.addAttribute("changeCount", new CountForm());
        return "storage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addStorage(Model model){
        model.addAttribute("storageForm", new AddStorageForm());
        return "addStorageForm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStorageForm(@ModelAttribute("storageForm") AddStorageForm form, BindingResult result){
        validatorAddStorageForm.validate(form, result);
        if (result.hasErrors()) {
            return "addStorageForm";
        } else {
            storageService.save(form);
            return "redirect:/admin/storage/all";
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String addStorage(@ModelAttribute("changeCount") CountForm form, @PathVariable("id") String id){
        Accounting accounting = accountingService.getById(form.getAccounting());
        AddCountForm addCountForm = new AddCountForm();
        addCountForm.setAccounting(accounting);
        addCountForm.setCount(form.getCount());
        accountingService.putCount(addCountForm);
        return "redirect:/admin/storage/" + id;
    }
}
