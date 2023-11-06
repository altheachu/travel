package ecommerce.travel.mapper;

import ecommerce.travel.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface OrderMapper {

    @Insert("INSERT INTO ORDERS (id, customer_id, order_date, order_amt) VALUES (#{id},#{customerId},#{orderDate},#{orderAmt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer createOrder(Order order);
}
