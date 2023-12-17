package ecommerce.travel.product.entity;

//import lombok.Data;

import lombok.Data;

import java.math.BigDecimal;

// 商品
@Data
public class Product {

    // 編號
    private Integer id;
    // 名稱
    private String name;
    // 單價
    private BigDecimal price;
    // 庫存
    private BigDecimal stockQty;
    // 商品類別
    private String type;
    // 刪除否
    private String disabled;

}
