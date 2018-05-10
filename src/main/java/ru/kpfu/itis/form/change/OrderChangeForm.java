package ru.kpfu.itis.form.change;

import ru.kpfu.itis.model.Order;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.OrderStatus;

public class OrderChangeForm {

    private Order order;
    private OrderStatus orderStatus;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
