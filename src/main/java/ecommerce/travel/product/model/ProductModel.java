package ecommerce.travel.product.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductModel {
    // 產品編號
    private Integer id;
    // 名稱
    private String name;
    // 單價
    private BigDecimal price;
    // 庫存
    private BigDecimal stockQty;
    // 商品類別
    private String type;
    // 圖片位址
    private String pdtUrl;
    // 圖片說明
    private String pdtAlt;
}
