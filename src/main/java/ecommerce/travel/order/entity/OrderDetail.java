package ecommerce.travel.order.entity;

import lombok.Data;

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
