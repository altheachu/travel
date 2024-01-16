package ecommerce.travel.customer.mapper;

import ecommerce.travel.customer.entity.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface CustomerMapper {

    @Insert("INSERT INTO CUSTOMER (id, name, phone, email) VALUES (#{id}, #{name}, #{phone}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer createCustomer(Customer customer);
}
