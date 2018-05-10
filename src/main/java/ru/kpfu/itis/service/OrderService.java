package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.change.OrderChangeForm;
import ru.kpfu.itis.model.Order;
import ru.kpfu.itis.model.ProductInOrder;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.OrderStatus;

import java.util.List;

@Service
public interface OrderService {

    List<Order> getAll();

    List<Order> getByUserId(long id);

    Order getById(long id);

    Order getByUserAndOrderStatus(User user, OrderStatus orderStatus);

    List<Order> getByUser(User user);

    void save(Order order);

    void save(User user, ProductInOrder productInOrder);

    void change(OrderChangeForm form);

    void delete(long id);

    void delete(List<Order> orders);

    int getResponseAboutMinusCount(long id, int count);

    int getResponseAboutPlusCount(long id, int count);

    List<Order> getForList(long id, List<OrderStatus> orderStatus);
}
