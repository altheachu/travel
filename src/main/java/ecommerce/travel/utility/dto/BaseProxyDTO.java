package ecommerce.travel.utility.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseProxyDTO implements Serializable {

    private String msgId;
    private String sendTime;

}
