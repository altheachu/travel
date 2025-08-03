package ecommerce.travel.product.mapper;

import ecommerce.travel.product.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Insert("INSERT INTO PRODUCT(id, name, price, stock_qty, type, pdt_url, pdt_alt, enabled) VALUES (#{id}, #{name}, #{price}, #{stockQty}, #{type}, #{pdtUrl}, #{pdtAlt}, #{enabled})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer createProduct(Product product);

    @Select("SELECT * FROM PRODUCT")
    @Results(id="findAllProduct", value={
            @Result(property = "id", column = "id", id=true),
            @Result(property = "name", column = "name", id=false),
            @Result(property = "price", column = "price", id=false),
            @Result(property = "stockQty", column = "stock_qty", id=false),
            @Result(property = "type", column = "type", id=false),
            @Result(property = "pdtUrl", column = "pdt_url", id=false),
            @Result(property = "pdtAlt", column = "pdt_alt", id=false),
            @Result(property = "enabled", column = "enabled", id=false)
    })
    List<Product> findAllProduct();

    @Select("SELECT * FROM PRODUCT WHERE name LIKE CONCAT('%', #{name}, '%')")
    @Results(id="findProductByName", value={
            @Result(property = "id", column = "id", id=true),
            @Result(property = "name", column = "name", id=false),
            @Result(property = "price", column = "price", id=false),
            @Result(property = "stockQty", column = "stock_qty", id=false),
            @Result(property = "type", column = "type", id=false),
            @Result(property = "pdtUrl", column = "pdt_url", id=false),
            @Result(property = "pdtAlt", column = "pdt_alt", id=false),
            @Result(property = "enabled", column = "enabled", id=false)
    })
    List<Product> findProductByName(String name);

    @Select("SELECT * FROM PRODUCT WHERE id = #{id}")
    @Results(id="findProductById", value={
            @Result(property = "id", column = "id", id=true),
            @Result(property = "name", column = "name", id=false),
            @Result(property = "price", column = "price", id=false),
            @Result(property = "stockQty", column = "stock_qty", id=false),
            @Result(property = "type", column = "type", id=false),
            @Result(property = "pdtUrl", column = "pdt_url", id=false),
            @Result(property = "pdtAlt", column = "pdt_alt", id=false),
            @Result(property = "enabled", column = "enabled", id=false)
    })
    Product findProductById(Integer id);

    @Update("UPDATE PRODUCT SET name = #{name}, price = #{price}, stock_qty = #{stockQty}, pdt_url = #{pdtUrl}, pdt_alt = #{pdtAlt}, type = #{type} WHERE id = #{id}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer updateProduct(Product product);

    @Update("UPDATE PRODUCT SET disabled = #{disabled} WHERE id = #{id}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer deleteProduct(Product product);

    @Update("UPDATE PRODUCT SET stock_qty = stock_qty - #{stockQty} WHERE id = #{id} and stock_qty >= #{stockQty}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer updateProductStock(Product product);
}
