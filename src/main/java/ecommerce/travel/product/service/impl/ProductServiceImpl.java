package ecommerce.travel.product.service.impl;

import ecommerce.travel.aop.EventLog;
import ecommerce.travel.aop.LogTime;
import ecommerce.travel.config.RabbitMQConfig;
import ecommerce.travel.order.entity.OrderDetail;
import ecommerce.travel.product.entity.Product;
import ecommerce.travel.product.mapper.ProductMapper;
import ecommerce.travel.product.model.ProductModel;
import ecommerce.travel.product.service.ProductService;
import ecommerce.travel.utility.dto.OrderDetailProxyDTO;
import ecommerce.travel.utility.dto.OrderEventProxyDTO;
import ecommerce.travel.utility.entity.Eventlog;
import ecommerce.travel.utility.service.EventlogService;
import ecommerce.travel.utility.utils.EventlogConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductMapper productMapper;

    private EventlogService eventlogService;

    public ProductServiceImpl(ProductMapper productMapper, EventlogService eventlogService){
        this.productMapper = productMapper;
        this.eventlogService = eventlogService;
    }

    @Override
    public Integer createProduct(ProductModel productModel) throws Exception{
        try {
            Product product = new Product();
            BeanUtils.copyProperties(productModel, product);
            return productMapper.createProduct(product);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ProductModel> findAllProduct() throws Exception {
        try {
            List<ProductModel> pdtVoList = new ArrayList<>();
            List<Product> pdtList = productMapper.findAllProduct();
            pdtList = pdtList.stream().filter(t->!t.getDisabled().equals("Y")).collect(Collectors.toList());
            for (Product pdt : pdtList){
                ProductModel productModel = new ProductModel();
                BeanUtils.copyProperties(pdt, productModel);
                pdtVoList.add(productModel);
            }
            return pdtVoList;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ProductModel> findProductByName(String name) throws Exception {
        try {
            List<ProductModel> pdtVoList = new ArrayList<>();
            List<Product> pdtList = productMapper.findProductByName(name);
            pdtList = pdtList.stream().filter(t->!t.getDisabled().equals("Y")).collect(Collectors.toList());
            for (Product pdt : pdtList){
                ProductModel productModel = new ProductModel();
                BeanUtils.copyProperties(pdt, productModel);
                pdtVoList.add(productModel);
            }
            return pdtVoList;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ProductModel findProductById(Integer id) throws Exception {
        try {
            ProductModel pdtVo = new ProductModel();
            Product product = productMapper.findProductById(id);
            if(!product.getDisabled().equals("Y")) {
                BeanUtils.copyProperties(product, pdtVo);
            }
            return pdtVo;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Integer updateProduct(ProductModel productModel) throws Exception{
        try {
            Product product = productMapper.findProductById(productModel.getId());
            if(!productModel.getName().equals(product.getName())){
                product.setName(productModel.getName());
            }
            if(!productModel.getPrice().equals(product.getPrice())){
                product.setPrice(productModel.getPrice());
            }
            if(!productModel.getPrice().equals(product.getPrice())){
                product.setPrice(productModel.getPrice());
            }
            if(!productModel.getStockQty().equals(product.getStockQty())){
                product.setStockQty(productModel.getStockQty());
            }
            if(!productModel.getType().equals(product.getType())){
                product.setType(productModel.getType());
            }
            return productMapper.updateProduct(product);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Integer deleteProduct(Integer id) throws Exception {
        try {
            Product product = productMapper.findProductById(id);
            product.setDisabled("Y");
            return productMapper.deleteProduct(product);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @RabbitListener(queues = {RabbitMQConfig.RABBITMQ_ORDER_TO_PRODUCT_TOPIC})
    @EventLog(logTime = LogTime.BEFORE_METHOD, type = EventlogConstant.receiveMsg)
    public void deductStockFromOrder(OrderEventProxyDTO orderEventProxyDTO) throws Exception{

        try {
            List<OrderDetailProxyDTO> orderDetailList = orderEventProxyDTO.getOrderDetailProxyDTOList();

            for (OrderDetailProxyDTO orderdetail : orderDetailList){
                Integer pdtId = orderdetail.getProductId();
                Integer orderQty = orderdetail.getOrderQty();
                Product product = productMapper.findProductById(pdtId);
                Integer currentQty = product.getStockQty().intValue() - orderQty;
                product.setStockQty(BigDecimal.valueOf(currentQty));
                productMapper.updateProduct(product);
            }

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
