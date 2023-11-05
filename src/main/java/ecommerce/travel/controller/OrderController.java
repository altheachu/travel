package ecommerce.travel.controller;

import ecommerce.travel.model.OrderModel;
import ecommerce.travel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Order")
@CrossOrigin(value = "http://localhost:8080")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public Integer addOrder(OrderModel orderModel){
        return orderService.createOrder(orderModel);
    }
}
