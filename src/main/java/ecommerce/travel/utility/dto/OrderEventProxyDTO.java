package ecommerce.travel.utility.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEventProxyDTO extends BaseProxyDTO{

    List<OrderDetailProxyDTO> orderDetailProxyDTOList;
}
