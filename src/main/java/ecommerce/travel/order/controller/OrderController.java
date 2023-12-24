package ecommerce.travel.order.controller;

import ecommerce.travel.order.model.OrderModel;
import ecommerce.travel.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin(value = "http://localhost:8080")
@Api(tags = "Order")
public class OrderController {

    private OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/add")
    @ApiOperation(value = "Add Order")
    public Boolean addOrder(@RequestBody OrderModel orderModel) throws Exception {
        try {
            Boolean isViewOrderAmtCorrect = orderService.checkOrderAmt(orderModel);
            if (!isViewOrderAmtCorrect){
                throw new Exception("The order amount on the view is not equal to calculation of server.");
            }
            return orderService.createOrder(orderModel);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
