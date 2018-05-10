package ru.kpfu.itis.util.transform;

import ru.kpfu.itis.form.add.AddCountForm;
import ru.kpfu.itis.model.Accounting;

public class AddCountFormTransform {

    public static Accounting transform(AddCountForm form) {
        Accounting accounting = new Accounting();
        Accounting accountingForm = form.getAccounting();
        accounting.setId(accountingForm.getId());
        accounting.setProduct(accountingForm.getProduct());
        accounting.setStorage(accountingForm.getStorage());
        accounting.setCount(form.getCount());
        return accounting;
    }
}
