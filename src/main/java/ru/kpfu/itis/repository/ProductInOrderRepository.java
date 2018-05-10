package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.method.P;
import ru.kpfu.itis.model.Order;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.ProductInOrder;

import java.util.List;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {

    List<ProductInOrder> findAll();

    ProductInOrder findById(long id);

    ProductInOrder findOneByProductId(long id);

    List<ProductInOrder> findAllByOrder(Order order);

    ProductInOrder findOneByOrderIdAndProductId(long oid, long pid);

    List<ProductInOrder> findAllByOrderIn(List<Order> orders);
}
