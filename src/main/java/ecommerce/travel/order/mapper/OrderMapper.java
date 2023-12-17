package ecommerce.travel.order.mapper;

import ecommerce.travel.order.entity.Order;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderMapper {

    @Insert("INSERT INTO ORDERS (id, customer_id, order_date, order_amt, daily_seqno) VALUES (#{id},#{customerId},#{orderDate},#{orderAmt},#{dailySeqno})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer createOrder(Order order);

    @Select("SELECT COUNT(*) FROM ORDERS WHERE customer_id = #{customerId} AND order_date = #{orderDate} AND order_amt = #{orderAmt}")
    Integer findOrderCnt(Order order);

    @Select("SELECT MAX(daily_seqno) FROM ORDERS WHERE customer_id = #{customerId} AND order_date = #{orderDate} AND order_amt = #{orderAmt}")
    Integer findOrderDailySeqno(Order order);

    @Select("SELECT id FROM ORDERS WHERE customer_id = #{customerId} AND order_date = #{orderDate} AND daily_seqno = #{dailySeqno}")
    Integer findOrderId(Order order);
}
