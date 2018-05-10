package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.change.OrderChangeForm;
import ru.kpfu.itis.message.Mail;
import ru.kpfu.itis.model.Order;
import ru.kpfu.itis.model.ProductInOrder;
import ru.kpfu.itis.model.Accounting;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.OrderStatus;
import ru.kpfu.itis.repository.OrderRepository;
import ru.kpfu.itis.repository.ProductInOrderRepository;
import ru.kpfu.itis.service.OrderService;
import ru.kpfu.itis.service.AccountingService;
import ru.kpfu.itis.util.transform.OrderChangeFormTransform;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductInOrderRepository productInOrderRepository;
    @Autowired
    private Mail mail;
    @Autowired
    private AccountingService accountingService;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getByUserId(long id) {
        return orderRepository.findAllByUserId(id);
    }

    @Override
    public Order getById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order getByUserAndOrderStatus(User user, OrderStatus orderStatus) {
        return orderRepository.findByUserAndOrderStatus(user, orderStatus);
    }

    @Override
    public List<Order> getByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }


    @Override
    public void save(User user, ProductInOrder productInOrder) {
        if (getByUserAndOrderStatus(user, OrderStatus.DIALED) != null) {
            Order order = getByUserAndOrderStatus(user, OrderStatus.DIALED);
            List<ProductInOrder> productInOrders = productInOrderRepository.findAllByOrder(order);
            boolean flag = false;
            for (ProductInOrder productInOrder1 : productInOrders) {
                if (productInOrder1.getProduct().getId() == productInOrder.getProduct().getId()) {
                    productInOrder1.setCount(productInOrder1.getCount() + 1);
                    Accounting accounting = accountingService.getByProduct(productInOrder.getProduct());
                    accounting.setCount(accounting.getCount() - 1);
                    accountingService.save(accounting);
                    flag = true;
                }
            }
            if (!flag) {
                productInOrder.setCount(1);
                productInOrders.add(productInOrder);
                Accounting accounting = accountingService.getByProduct(productInOrder.getProduct());
                accounting.setCount(accounting.getCount() - 1);
                accountingService.save(accounting);
            }
            order.setProductInOrders(productInOrders);
            orderRepository.save(order);
            for (ProductInOrder productInOrder1 : productInOrders) {
                productInOrder1.setOrder(orderRepository.findById(order.getId()));
                productInOrderRepository.save(productInOrder1);
            }
        } else {
            List<ProductInOrder> productInOrders = new LinkedList<>();
            Order order = new Order();
            order.setOrderStatus(OrderStatus.DIALED);
            order.setUser(user);
            productInOrder.setCount(1);
            productInOrders.add(productInOrder);
            order.setProductInOrders(productInOrders);
            orderRepository.save(order);
            productInOrder.setOrder(order);
            productInOrderRepository.save(productInOrder);
            Accounting accounting = accountingService.getByProduct(productInOrder.getProduct());
            accounting.setCount(accounting.getCount() - 1);
            accountingService.save(accounting);
        }


    }


    @Override
    public void change(OrderChangeForm form) {
        Order order = OrderChangeFormTransform.transform(form);
        if (order.getOrderStatus() == OrderStatus.SEARS) {
            mail.sendMail("from@no-spam.com", order.getUser().getEmail(),
                    "Shop",
                    "Ваш товар собирается");
        } else if (order.getOrderStatus() == OrderStatus.COMPLETED) {
            mail.sendMail("from@no-spam.com", order.getUser().getEmail(),
                    "Shop",
                    "Ваш товар отправлен");
        } else if (order.getOrderStatus() == OrderStatus.FORMAD) {
            mail.sendMail("from@no-spam.com", order.getUser().getEmail(),
                    "Shop",
                    "Ваш заказ отправлен на сборку");
        }
        orderRepository.save(order);
    }

    @Override
    public void delete(long id) {
        orderRepository.delete(id);
    }

    @Override
    public void delete(List<Order> orders) {
        orderRepository.deleteInBatch(orders);
    }

    public int getResponseAboutMinusCount(long id, int count) {
        int availableCount = accountingService.getByProductId(id).getCount();
        int neededCount = count - 1;
        return setResponse(id, neededCount, availableCount, "minus", count);
    }

    public int getResponseAboutPlusCount(long id, int count) {
        int availableCount = accountingService.getByProductId(id).getCount();
        int neededCount = count + 1;
        return setResponse(id, neededCount, availableCount, "plus", count);
    }

    @Override
    public List<Order> getForList(long id, List<OrderStatus> orderStatus) {
        return orderRepository.findByUserIdAndOrderStatusIn(id, orderStatus);
    }

    private int setResponse(long id, int neededCount, int availableCount, String actionName, int count) {
        if (neededCount > availableCount + count)
            return -1;
        else if (neededCount == 0)
            return -2;
        else if (neededCount <= availableCount + neededCount) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Accounting accounting = accountingService.getByProductId(id);
            Order order = getByUserAndOrderStatus(user, OrderStatus.DIALED);
            ProductInOrder productInOrder = productInOrderRepository.findOneByOrderIdAndProductId(order.getId(), id);
            if (Objects.equals(actionName, "plus")) {
                productInOrder.setCount(productInOrder.getCount() + 1);
                productInOrderRepository.save(productInOrder);
                accounting.setCount(accounting.getCount() - 1);
                accountingService.save(accounting);
                return neededCount;
            }
            if (Objects.equals(actionName, "minus")) {
                productInOrder.setCount(productInOrder.getCount() - 1);
                productInOrderRepository.save(productInOrder);
                accounting.setCount(accounting.getCount() + 1);
                accountingService.save(accounting);
                return neededCount;
            }
        }
        System.out.println("Пользователь сломал AJAX программа не успивает за кликами");
        return 0;
    }

}
