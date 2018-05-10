package ru.kpfu.itis.util.transform;

import ru.kpfu.itis.form.add.AddProductForm;
import ru.kpfu.itis.model.Product;

public class AddProductFormTransform {

    public static Product transform(AddProductForm form) {
        Product product = new Product();
        product.setName(form.getName());
        product.setDescription(form.getDescription());
        product.setPrice(form.getPrice());
        product.setType(form.getType());
        return product;
    }
}
