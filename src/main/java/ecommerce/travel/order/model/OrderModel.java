package ecommerce.travel.order.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderModel {
    // 訂單編號
    private Integer orderId;
    // 客戶編號
    private Integer customerId;
    // 訂購時間
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date orderDate;
    // 支付金額
    private BigDecimal orderAmt;
    // 訂單內容清單
    private List<OrderDetailModel> orderDetailList;
    // 訂單取消否
    private String cancelFlag;
}
