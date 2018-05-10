package ru.kpfu.itis.util.transform;

import ru.kpfu.itis.form.ProductForm;
import ru.kpfu.itis.model.Product;

public class ProductChangeFormTransform {

    public Product transform(ProductForm form) {
        Product product = new Product();
        Product product1 = form.getProduct();
        product.setName(form.getName());
        product.setDescription(form.getDescription());
        product.setPrice(form.getPrice());
        product.setType(form.getType());
        product.setId(form.getId());
        if (form.getDescription() == null || form.getDescription().isEmpty()) {
            product.setDescription(product1.getDescription());
        }
        if (form.getName() == null || form.getName().isEmpty()) {
            product.setName(product1.getName());
        }
        return product;
    }
}
