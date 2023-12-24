package ecommerce.travel.order.service.impl;

import ecommerce.travel.order.entity.Order;
import ecommerce.travel.order.entity.OrderDetail;
import ecommerce.travel.order.mapper.OrderDetailMapper;
import ecommerce.travel.order.mapper.OrderMapper;
import ecommerce.travel.order.model.OrderDetailModel;
import ecommerce.travel.order.model.OrderModel;
import ecommerce.travel.order.service.OrderProxyService;
import ecommerce.travel.order.service.OrderService;
import ecommerce.travel.util.OrderDetailProxyDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private OrderProxyService orderProxyService;

    @Override
    public Boolean createOrder(OrderModel orderModel) throws Exception{
        try {
            boolean isOrderSucess = false;
            // 查詢當天是否已有消費資料
            Integer dailySeqno = 1;
            Order order = new Order();
            BeanUtils.copyProperties(orderModel, order);
            order.setOrderDate(new Date(orderModel.getOrderDate().getTime()));
            Integer todayOrderCnt = orderMapper.findOrderCnt(order);
            if (todayOrderCnt != 0){
                dailySeqno = orderMapper.findOrderDailySeqno(order);
            }
            order.setDailySeqno(dailySeqno);
            Integer orderSuccessQty = orderMapper.createOrder(order);
            if (orderSuccessQty < 0){
                throw new Exception("新增訂單失敗");
            } else {
                List<OrderDetailModel> orderDetailList = orderModel.getOrderDetailList();
                if(orderDetailList == null ){
                    throw new Exception("新增訂單失敗，無訂單明細");
                } else {
                    Integer orderId = orderMapper.findOrderId(order);
                    for (OrderDetailModel orderDetailModel : orderDetailList){
                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.setOrderId(orderId);
                        orderDetail.setProductId(orderDetailModel.getProductId());
                        orderDetail.setOrderQty(orderDetailModel.getOrderQty());
                        orderDetailMapper.createOrderDetail(orderDetail);
                    }
                    // 發送扣庫 Event
                    OrderDetailProxyDTO dto = new OrderDetailProxyDTO();
                    BeanUtils.copyProperties(orderDetailList.get(0), dto);
                    orderProxyService.deductProductStock(dto);
                    isOrderSucess = true;
                }
            }
            return isOrderSucess;
        }catch (Exception e){
            throw new Exception("新增訂單失敗: " + e.getMessage());
        }
    }
}
