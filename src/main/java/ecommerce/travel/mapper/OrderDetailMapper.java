package ecommerce.travel.mapper;

import ecommerce.travel.entity.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface OrderDetailMapper {

    @Insert("INSERT INTO ORDERSDETAIL (id, order_id, product_id, order_qty) VALUES (#{id},#{orderId},#{productId},#{orderQty})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer createOrderDetail(OrderDetail orderDetail);
}
