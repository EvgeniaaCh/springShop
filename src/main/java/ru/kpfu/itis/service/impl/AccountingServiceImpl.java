package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.add.AddCountForm;
import ru.kpfu.itis.form.add.AddAccountingForm;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.Accounting;
import ru.kpfu.itis.repository.AccountingRepository;
import ru.kpfu.itis.service.ProductService;
import ru.kpfu.itis.service.AccountingService;
import ru.kpfu.itis.util.transform.AddCountFormTransform;
import ru.kpfu.itis.util.transform.AddAccountingFormTransform;

import java.util.LinkedList;
import java.util.List;

@Service
public class AccountingServiceImpl implements AccountingService {

    @Autowired
    private AccountingRepository accountingRepository;
    private AccountingService accountingService;
    private ProductService productService = new ProductServiceImpl();

    @Override
    public List<Accounting> getAll() {
        return accountingRepository.findAll();
    }

    @Override
    public Accounting getById(long id) {
        return accountingRepository.findById(id);
    }

    @Override
    public Accounting getByProduct(Product product) {
        return accountingRepository.findByProduct(product);
    }

    @Override
    public Accounting getByProductId(long id) {
        return accountingRepository.findByProductId(id);
    }

    @Override
    public List<Accounting> getAllByStorageId(long id) {
        return accountingRepository.findAllBystorageId(id);
    }

    @Override
    public List<Accounting> getAllByProductId(long id) {
        return accountingRepository.findAllByProductId(id);
    }

    @Override
    public List<Accounting> getAllByProductName(String name) {
        return accountingRepository.findAllByProductName(name);
    }

    @Override
    public int getAllCountOnStorage(List<Accounting> accounting) {
        int count = 0;
        for (Accounting acc : accounting) {
            count += acc.getCount();
        }
        return count;
    }

    @Override
    public List<Long> getIdsProducts(List<Accounting> accounting) {
        List<Long> ids = new LinkedList<Long>();
        for (Accounting acc : accounting) {
            ids.add(acc.getId());
        }
        return ids;
    }

    @Override
    public void save(AddAccountingForm form) {
        Accounting accounting = AddAccountingFormTransform.transform(form);
        accountingRepository.save(accounting);
    }

    @Override
    public void save(Accounting accounting) {
        accountingRepository.save(accounting);
    }

    @Override
    public void putCount(AddCountForm form) {
        Accounting accounting = AddCountFormTransform.transform(form);
        accountingRepository.save(accounting);
    }

    @Override
    public void delete(long id) {
        accountingRepository.delete(id);
    }


}
