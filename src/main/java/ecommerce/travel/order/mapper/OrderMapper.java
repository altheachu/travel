package ecommerce.travel.order.mapper;

import ecommerce.travel.order.entity.Order;
import ecommerce.travel.product.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.Map;

@Mapper
public interface OrderMapper {

    @Insert("INSERT INTO ORDERS (id, customer_id, order_date, order_amt, daily_seqno, cancel_flag) VALUES (#{id}, #{customerId}, #{orderDate}, #{orderAmt}, #{dailySeqno}, #{cancelFlag})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer createOrder(Order order);

    @Select("SELECT COUNT(*) FROM ORDERS WHERE customer_id = #{customerId} AND order_date = #{orderDate} ")
    Integer findOrderCnt(Order order);

    @Select("SELECT MAX(daily_seqno) FROM ORDERS WHERE customer_id = #{customerId} AND order_date = #{orderDate}")
    Integer findOrderDailySeqno(Order order);

    @Select("SELECT id FROM ORDERS WHERE customer_id = #{customerId} AND order_date = #{orderDate} AND daily_seqno = #{dailySeqno}")
    Integer findOrderId(Order order);

    @Select("SELECT * FROM ORDERS WHERE id = #{id}")
    @Results(id="findOrderById", value={
            @Result(property = "id", column = "id", id=true),
            @Result(property = "customerId", column = "customer_id", id=false),
            @Result(property = "orderDate", column = "order_date", id=false),
            @Result(property = "orderAmt", column = "order_amt", id=false),
            @Result(property = "dailySeqno", column = "daily_seqno", id=false),
            @Result(property = "cancelFlag", column = "cancel_flag", id=false),
    })
    Order findOrderById(@Param("id") Integer id);

    @Update("UPDATE ORDERS SET cancel_flag = #{cancelFlag} WHERE id = #{id}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer deleteOrderById(Map<String, Object> paramMap);
}
