package ecommerce.travel.customer.controller;

import ecommerce.travel.customer.model.CustomerModel;
import ecommerce.travel.customer.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin(value = "http://localhost:8080")
@Api(tags = "Customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/add")
    @ApiOperation("Add Customer")
    public Integer addCustomer(@RequestBody CustomerModel customerModel) throws Exception {
        try {
            return customerService.createCustomer(customerModel);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
