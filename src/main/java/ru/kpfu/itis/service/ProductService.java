package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.add.AddProductForm;
import ru.kpfu.itis.form.ProductForm;
import ru.kpfu.itis.model.Product;

import java.util.List;

@Service
public interface ProductService {

    List<Product> getAll();

    Product getById(long id);

    List<Product> getAllByName(String name);

    List<Product> getByIdIn(List<Long> ids);

    void save(AddProductForm form);

    void change(ProductForm form);
}
