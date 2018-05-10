package ru.kpfu.itis.util.transform;

import ru.kpfu.itis.form.change.OrderChangeForm;
import ru.kpfu.itis.model.Order;

public class OrderChangeFormTransform {

    public static Order transform(OrderChangeForm form){
        Order order = form.getOrder();
        order.setOrderStatus(form.getOrderStatus());
        return order;
    }
}
