package ecommerce.travel.customer.mapper;

import ecommerce.travel.customer.entity.Customer;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CustomerMapper {

    @Insert("INSERT INTO CUSTOMER (id, name, phone, email) VALUES (#{id}, #{name}, #{phone}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer createCustomer(Customer customer);

    @Select("SELECT MAX(id) FROM CUSTOMER")
    Integer findCustomerId();

    @Select("SELECT * FROM CUSTOMER WHERE id = #{id}")
    @Results(id="findProductById", value={
            @Result(property = "id", column = "id", id=true),
            @Result(property = "name", column = "name", id=false),
            @Result(property = "phone", column = "phone", id=false),
            @Result(property = "email", column = "email", id=false),
    })
    Customer findCustomerById(Integer id);
}
