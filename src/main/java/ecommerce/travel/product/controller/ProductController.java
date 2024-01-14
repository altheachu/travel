package ecommerce.travel.product.controller;

import ecommerce.travel.product.model.ProductModel;
import ecommerce.travel.product.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(value = "http://localhost:8080")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/add")
    @ApiOperation(value = "Add Product")
    public Integer createProduct(@RequestBody ProductModel productModel) throws Exception{
        try{
            return productService.createProduct(productModel);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/findAll")
    @ApiOperation(value = "Find All Product")
    public List<ProductModel> findAllProduct() throws Exception {
        try{
            return productService.findAllProduct();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/findByName")
    @ApiOperation(value = "Find All Product")
    public List<ProductModel> findProductByName(@RequestParam(name = "name") String name) throws Exception {
        try{
            return productService.findProductByName(name);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "Find Product By Id")
    public ProductModel findProductById(@PathVariable Integer id) throws Exception {
        try{
            return productService.findProductById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "Update Product Info")
    @PostMapping("/update")
    public Integer update(@RequestBody ProductModel productModel) throws Exception{
        try{
            return productService.updateProduct(productModel);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @ApiOperation(value = "Delete Product By Id")
    @PostMapping("/delete/{id}")
    public Integer delete(@PathVariable Integer id) throws Exception{
        try{
            return productService.deleteProduct(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
