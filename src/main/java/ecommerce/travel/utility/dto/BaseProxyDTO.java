package ecommerce.travel.utility.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseProxyDTO implements Serializable {

    private String msgId;
    private String sendTime;

}
