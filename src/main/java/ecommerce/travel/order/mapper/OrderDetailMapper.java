package ecommerce.travel.order.mapper;

import ecommerce.travel.order.entity.OrderDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    @Insert("INSERT INTO ORDERSDETAIL (id, order_id, product_id, order_qty) VALUES (#{id}, #{orderId}, #{productId}, #{orderQty})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer createOrderDetail(OrderDetail orderDetail);

    @Select("SELECT * FROM ORDERSDETAIL WHERE order_id = #{orderId}")
    @Results(id="findOrderDetailByOrderId", value={
            @Result(property = "id", column = "id", id=true),
            @Result(property = "orderId", column = "order_id", id=false),
            @Result(property = "productId", column = "product_id", id=false),
            @Result(property = "orderQty", column = "order_qty", id=false),
    })
    List<OrderDetail> findOrderDetailByOrderId(@Param("orderId") Integer orderId);
}
