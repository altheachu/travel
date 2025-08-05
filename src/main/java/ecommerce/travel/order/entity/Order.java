package ecommerce.travel.order.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.sql.Date;

// 訂單
@Data
public class Order {

    // 編號
    private Integer id;
    // 客戶編號
    private Integer customerId;
    // 訂購時間
    private Date orderDate;
    // 支付金額
    private BigDecimal orderAmt;
    // 當天內第N次消費
    private Integer dailySeqno;
    // 訂單取消否
    private String cancelFlag;
    // 配送狀態 1 檢貨中 2 已成立 3已發貨 4已寄送
    private Integer status;

}
