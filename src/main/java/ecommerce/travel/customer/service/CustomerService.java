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
}
