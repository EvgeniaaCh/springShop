package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.Accounting;

import java.util.List;

@Repository
public interface AccountingRepository extends JpaRepository<Accounting, Long> {

    List<Accounting> findAll();

    Accounting findById(long id);

    List<Accounting> findAllBystorageId(long id);

    List<Accounting> findAllByProductId(long id);

    List<Accounting> findAllByProductName(String name);

    Accounting findByProduct(Product product);

    Accounting findByProductId(long id);
}
