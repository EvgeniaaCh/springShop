package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.add.AddProductForm;
import ru.kpfu.itis.form.ProductForm;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.repository.ProductRepository;
import ru.kpfu.itis.service.ProductService;
import ru.kpfu.itis.util.transform.AddProductFormTransform;
import ru.kpfu.itis.util.transform.ProductChangeFormTransform;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllByName(String name) {
        return productRepository.findAllByName(name);
    }

    @Override
    public List<Product> getByIdIn(List<Long> ids) {
        return productRepository.findByIdIn(ids);
    }

    @Override
    public void save(AddProductForm form) {
        Product product = AddProductFormTransform.transform(form);
        productRepository.save(product);
    }

    @Override
    public void change(ProductForm form) {
        ProductChangeFormTransform productChangeFormTransform = new ProductChangeFormTransform();
        Product product = productChangeFormTransform.transform(form);
        productRepository.save(product);
    }
}
