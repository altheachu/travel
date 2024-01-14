package ecommerce.travel.customer.entity;

import lombok.Data;

@Data
public class Customer {

    // 編號
    private Integer id;
    // 名稱
    private String name;
    // 電話
    private String phone;
    // 電子郵件
    private String email;

}
