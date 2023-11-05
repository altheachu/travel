package ecommerce.travel.model;

import lombok.Data;

@Data
public class OrderDetailModel {
    // 商品編號
    private Integer productId;
    // 訂購數量
    private Integer orderQty;
}
