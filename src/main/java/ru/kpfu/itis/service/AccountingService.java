package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.add.AddCountForm;
import ru.kpfu.itis.form.add.AddAccountingForm;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.Accounting;

import java.util.List;

@Service
public interface AccountingService {

    List<Accounting> getAll();

    Accounting getById(long id);

    Accounting getByProduct(Product product);

    Accounting getByProductId(long id);

    List<Accounting> getAllByStorageId(long id);

    List<Accounting> getAllByProductId(long id);

    List<Accounting> getAllByProductName(String name);

    int getAllCountOnStorage(List<Accounting> accounting);

    List<Long> getIdsProducts(List<Accounting> accounting);

    void save(AddAccountingForm form);

    void save(Accounting accounting);

    void putCount(AddCountForm form);

    void delete(long id);
}
