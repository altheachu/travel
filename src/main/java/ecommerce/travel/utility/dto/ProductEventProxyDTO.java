package ecommerce.travel.utility.dto;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEventProxyDTO extends BaseProxyDTO{

    private Integer orderId;
    private Integer status;

}
