package ecommerce.travel.customer.controller;

import ecommerce.travel.customer.service.CustomerService;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }


}
