package com.creativelabs.shoppingapi.services;

import com.creativelabs.shoppingapi.entities.Cart;
import com.creativelabs.shoppingapi.entities.Order;
import com.creativelabs.shoppingapi.entities.OrderItem;
import com.creativelabs.shoppingapi.repositories.CartRepository;
import com.creativelabs.shoppingapi.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

//    List<Order> orders = new ArrayList<>();
//
//    {
//        for (int i = 1; i <= 10; i++) {
//            int[] array = {1, 2, 3};
//            int rnd = new Random().nextInt(array.length);
//            orders.add(new Order(i, "XXX-" + i, Math.random() * 1000,
//                    "", array[rnd]));
//        }
//    }

    @Override
    public List<Order> getOrders(int status) {
        if (status == -1) {
            return orderRepository.findAll();
        }
        return orderRepository.findAllByStatus(status);
    }

    @Override
    public List<Order> getOrdersByUser(int status, int userId) {
        if (status == -1) {
            return orderRepository.findAllByUserId(userId);
        }
        return orderRepository.findAllByStatusAndUserId(status, userId);
    }

    @Override
    public Order saveOrder(int userId) {

        List<Cart> carts = cartRepository.getAllByUserId(userId);

        Set<OrderItem> orderItems = new HashSet<>();
        AtomicReference<Double> totalAmount = new AtomicReference<>((double) 0);

        Order order = new Order();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        order.setOrderDate(dtf.format(now));
        order.setCode(UUID.randomUUID().toString());
        order.setStatus(1);
        order.setUserId(userId);

        carts.forEach(cart -> {
            OrderItem item = new OrderItem();
            item.setProductId(cart.getProductId());
            item.setImage(cart.getImage());
            item.setProductName(cart.getProductName());
            item.setProductPrice(cart.getProductPrice());
            item.setQuantity(cart.getQuantity());
            item.setTotalPrice(cart.getTotalPrice());
            item.setOrder(order);

            totalAmount.updateAndGet(v -> v + cart.getTotalPrice());
            orderItems.add(item);
        });

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount.get());

        return orderRepository.save(order);
    }
}
