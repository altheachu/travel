package ecommerce.travel.product.service;

import ecommerce.travel.product.model.ProductModel;

import java.util.List;

public interface ProductService {

    Integer createProduct(ProductModel productModel) throws Exception;

    List<ProductModel> findAllProduct() throws Exception;

    List<ProductModel> findProductByName(String name) throws Exception;

    ProductModel findProductById(Integer id) throws Exception;

    Integer updateProduct(ProductModel productModel) throws Exception;

    Integer deleteProduct(Integer id) throws Exception;
}
