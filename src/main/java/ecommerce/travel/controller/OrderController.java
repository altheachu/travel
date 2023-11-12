package ecommerce.travel.controller;

import ecommerce.travel.model.OrderModel;
import ecommerce.travel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin(value = "http://localhost:8080")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/add")
    public Boolean addOrder(@RequestBody OrderModel orderModel) throws Exception {
        // TODO 核算金額
        return orderService.createOrder(orderModel);
    }
}
