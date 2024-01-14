package ecommerce.travel.product.service;

import ecommerce.travel.product.model.ProductModel;

import java.util.List;

public interface ProductService {

    /*
    description: create a product
    author: Althea Chu
    params: productModel
    lastModified: 2024-01-14
    */
    Integer createProduct(ProductModel productModel) throws Exception;

    /*
    description: find all product
    author: Althea Chu
    lastModified: 2024-01-14
    */
    List<ProductModel> findAllProduct() throws Exception;

    /*
    description: find a product information by product name
    author: Althea Chu
    params: name
    lastModified: 2023-01-14
    */
    List<ProductModel> findProductByName(String name) throws Exception;

    /*
    description: find a product information by Id
    author: Althea Chu
    params: id
    lastModified: 2023-12-24
    */
    ProductModel findProductById(Integer id) throws Exception;

    /*
    description: update a product
    author: Althea Chu
    params: productModel
    lastModified: 2024-01-14
    */
    Integer updateProduct(ProductModel productModel) throws Exception;

    /*
    description: delete a product
    author: Althea Chu
    params: id
    lastModified: 2024-01-14
    */
    Integer deleteProduct(Integer id) throws Exception;
}
