package ecommerce.travel.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailProxyDTO implements Serializable {
    // 商品編號
    private Integer productId;
    // 訂購數量
    private Integer orderQty;
}
