package ecommerce.travel.utility.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailProxyDTO implements Serializable {

    private Integer productId;
    private Integer orderQty;

}
