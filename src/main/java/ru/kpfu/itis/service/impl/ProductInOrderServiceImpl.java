package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.Order;
import ru.kpfu.itis.model.ProductInOrder;
import ru.kpfu.itis.repository.ProductInOrderRepository;
import ru.kpfu.itis.service.OrderService;
import ru.kpfu.itis.service.ProductInOrderService;

import java.util.List;
import java.util.Random;

@Service
public class ProductInOrderServiceImpl implements ProductInOrderService {

    @Autowired
    private ProductInOrderRepository productInOrderRepository;


    @Override
    public List<ProductInOrder> getAll() {
        return productInOrderRepository.findAll();
    }

    @Override
    public ProductInOrder getById(long id) {
        return productInOrderRepository.findById(id);
    }

    @Override
    public List<ProductInOrder> getAllByOrder(Order order) {
        return productInOrderRepository.findAllByOrder(order);
    }

    @Override
    public List<ProductInOrder> getAllByOrderIn(List<Order> orders) {
        return productInOrderRepository.findAllByOrderIn(orders);
    }

    @Override
    public void returnProducts(List<ProductInOrder> productInOrders) {

    }

    @Override
    public void delete(long id) {
        productInOrderRepository.delete(id);
    }

    @Override
    public void delete(List<ProductInOrder> productInOrders) {
        productInOrderRepository.deleteInBatch(productInOrders);
    }
}
