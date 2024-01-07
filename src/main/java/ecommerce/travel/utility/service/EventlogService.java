package ecommerce.travel.utility.service;

import ecommerce.travel.product.model.ProductModel;
import ecommerce.travel.utility.entity.Eventlog;

public interface EventlogService {

    /*
    description: find a product information by Id
    author: Althea Chu
    params: id
    lastModified: 2024-01-07
    */
    Integer updateEventLog(Eventlog eventlog) throws Exception;
}
