package ecommerce.travel.order.service.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rabbitmq.tools.json.JSONUtil;
import ecommerce.travel.order.entity.Order;
import ecommerce.travel.order.entity.OrderDetail;
import ecommerce.travel.order.mapper.OrderDetailMapper;
import ecommerce.travel.order.mapper.OrderMapper;
import ecommerce.travel.order.model.OrderDetailModel;
import ecommerce.travel.order.model.OrderModel;
import ecommerce.travel.order.service.OrderProxyService;
import ecommerce.travel.order.service.OrderService;
import ecommerce.travel.utility.dto.OrderDetailProxyDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderMapper orderMapper;
    private OrderDetailMapper orderDetailMapper;
    private OrderProxyService orderProxyService;

    public OrderServiceImpl(OrderMapper orderMapper, OrderDetailMapper orderDetailMapper, OrderProxyService orderProxyService){
        this.orderMapper = orderMapper;
        this.orderDetailMapper = orderDetailMapper;
        this.orderProxyService = orderProxyService;
    }

    @Override
    public OrderModel findOrderById(Integer id) throws Exception {
        Order order = orderMapper.findOrderById(id);
        OrderModel orderModel = new OrderModel();
        BeanUtils.copyProperties(order, orderModel);
        List<OrderDetail> orderDetails =orderDetailMapper.findOrderDetailByOrderId(order.getId());
        List<OrderDetailModel> orderDetailModels = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails){
            OrderDetailModel orderDetailModel = new OrderDetailModel();
            BeanUtils.copyProperties(orderDetail, orderDetailModel);
            orderDetailModels.add(orderDetailModel);
        }
        orderModel.setOrderDetailList(orderDetailModels);
        return orderModel;
    }

    @Override
    public Boolean checkOrderAmt(OrderModel orderModel) throws Exception {
        boolean isPass = false;
        BigDecimal orderAmtCal = BigDecimal.ZERO;
        List<Integer> productIds = orderModel.getOrderDetailList().stream().map(t->t.getProductId()).collect(Collectors.toList());
        Map<Integer,BigDecimal> productPriceMap = orderProxyService.findProductPriceById(productIds);

        for(OrderDetailModel orderDetail : orderModel.getOrderDetailList()){
            BigDecimal itemAmt = productPriceMap.get(orderDetail.getProductId()).multiply(new BigDecimal(orderDetail.getOrderQty()));
            orderAmtCal = orderAmtCal.add(itemAmt);
        }

        if(orderModel.getOrderAmt().equals(orderAmtCal)){
            isPass = true;
        }

        return isPass;
    }

    @Override
    public Boolean createOrder(OrderModel orderModel) throws Exception{
        try {
            OrderModel completeOrderModel = null;
            boolean isOrderSucess = false;
            Integer dailySeqno = 1;
            Order order = new Order();
            BeanUtils.copyProperties(orderModel, order);
            order.setOrderDate(new Date(orderModel.getOrderDate().getTime()));
            Integer todayOrderCnt = orderMapper.findOrderCnt(order);
            if (todayOrderCnt != 0){
                dailySeqno = orderMapper.findOrderDailySeqno(order) + 1;
            }
            order.setDailySeqno(dailySeqno);
            Integer orderSuccessQty = orderMapper.createOrder(order);
            if (orderSuccessQty < 0){
                throw new Exception("Fail to create order");
            }
            List<OrderDetailModel> orderDetailList = orderModel.getOrderDetailList();
            if(orderDetailList == null ){
                throw new Exception("Fail to create order due to lack of order details");
            }
            Integer orderId = orderMapper.findOrderId(order);
            List<OrderDetailProxyDTO> orderDetailProxyDtoList = new ArrayList<>();
            OrderDetailProxyDTO orderProxyDto = new OrderDetailProxyDTO();
            for (OrderDetailModel orderDetailModel : orderDetailList){
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(orderId);
                orderDetail.setProductId(orderDetailModel.getProductId());
                orderDetail.setOrderQty(orderDetailModel.getOrderQty());
                orderDetailMapper.createOrderDetail(orderDetail);
                BeanUtils.copyProperties(orderDetail, orderProxyDto);
                orderDetailProxyDtoList.add(orderProxyDto);
            }
            // publish event to product module and deduct stock
            isOrderSucess = orderProxyService.deductProductStock(orderDetailProxyDtoList);
            // TODO compose front-end info
            if(isOrderSucess){
                Order completeOrder = orderMapper.findOrderById(orderId);
                List<OrderDetail> OrderDetail = orderDetailMapper.findOrderDetailByOrderId(completeOrder.getId());
            }
            return isOrderSucess;
        }catch (Exception e){
            throw new Exception("Fail to create order: " + e.getMessage());
        }
    }

    @Override
    public Integer deleteOrderById(Integer id) throws Exception {
        try {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("cancelFlag","Y");
            paramMap.put("id",id);
            return orderMapper.deleteOrderById(paramMap);
        } catch (Exception e){
            throw new Exception("Fail to delete order: " + e.getMessage());
        }
    }
}
