package ecommerce.travel.controller;

import ecommerce.travel.entity.Product;
import ecommerce.travel.model.ProductModel;
import ecommerce.travel.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(value = "http://localhost:8080")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/add")
    public Integer createProduct(ProductModel productModel) throws Exception{
        try{
            return productService.createProduct(productModel);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public List<ProductModel> findAllProduct() throws Exception {
        try{
            return productService.findAllProduct();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/findByName")
    public List<ProductModel> findProductByName(String name) throws Exception {
        try{
            return productService.findProductByName(name);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/findById")
    public ProductModel findProductById(Integer id) throws Exception {
        try{
            return productService.findProductById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/update")
    public Integer update(ProductModel productModel) throws Exception{
        try{
            return productService.updateProduct(productModel);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public Integer delete(Integer id) throws Exception{
        try{
            return productService.deleteProduct(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
