package ru.kpfu.itis.form.add;

import org.springframework.security.access.method.P;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.Storage;
import ru.kpfu.itis.service.ProductService;
import ru.kpfu.itis.service.StorageService;
import ru.kpfu.itis.service.impl.ProductServiceImpl;
import ru.kpfu.itis.service.impl.StorageServiceImpl;

public class AddAccountingForm {

    private Product product;
    private Storage storage;
    private int count;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
