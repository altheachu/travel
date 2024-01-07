package ecommerce.travel.order.controller;

import ecommerce.travel.order.model.OrderModel;
import ecommerce.travel.order.service.OrderService;
import ecommerce.travel.product.model.ProductModel;
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
    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Find An Order By Order ID")
    public OrderModel findOrderById(@PathVariable Integer id) throws Exception {
        try{
            return orderService.findOrderById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "Delete An Order By Order ID")
    public Integer deleteOrderById(@PathVariable Integer id) throws Exception{
        try{
            return orderService.deleteOrderById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
