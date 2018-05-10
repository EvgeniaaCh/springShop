package ru.kpfu.itis.util.transform;

import ru.kpfu.itis.form.add.AddAccountingForm;
import ru.kpfu.itis.model.Accounting;


public class AddAccountingFormTransform {

    public static Accounting transform(AddAccountingForm form) {
        Accounting accounting = new Accounting();
        accounting.setProduct(form.getProduct());
        accounting.setStorage(form.getStorage());
        accounting.setCount(form.getCount());
        return accounting;
    }

}
