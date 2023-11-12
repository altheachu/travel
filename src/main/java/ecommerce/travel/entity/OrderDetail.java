package ecommerce.travel.entity;

//import lombok.Data;

import lombok.Data;

import java.math.BigDecimal;

// 訂單細節
@Data
public class OrderDetail {

    // 編號
    private Integer id;
    // 訂單編號
    private Integer orderId;
    // 商品編號
    private Integer productId;
    // 訂購數量
    private Integer orderQty;

}
