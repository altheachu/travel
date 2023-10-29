package ecommerce.travel.entity;

//import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

// 訂單
//@Data
public class Order {

    // 編號
    private Integer id;
    // 客戶編號
    private Integer customerId;
    // 訂購時間
    private Date orderDate;
    // 支付金額
    private BigDecimal orderAmt;

}
