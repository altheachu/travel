package ecommerce.travel.customer.service.impl;

import ecommerce.travel.customer.entity.Customer;
import ecommerce.travel.customer.mapper.CustomerMapper;
import ecommerce.travel.customer.model.CustomerModel;
import ecommerce.travel.customer.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerMapper customerMapper;
    public CustomerServiceImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }
    @Override
    public Integer createCustomer(CustomerModel customerModel) throws Exception {
        try {
            Boolean isCreateSuccess = false;
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerModel, customer);
            return customerMapper.createCustomer(customer);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
