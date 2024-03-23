package ecommerce.travel.customer.service;

import ecommerce.travel.customer.model.CustomerModel;

public interface CustomerService {

    /*
    description: create info for a new customer
    author: Althea Chu
    params: customerModel
    lastModified: 2023-01-16
    */
    Integer createCustomer(CustomerModel customerModel) throws Exception;

    /*
    description: use customer id to find customer info
    author: Althea Chu
    params: id
    lastModified: 2024-02-05
    */
    CustomerModel findCustomerById(Integer id) throws Exception;
}
