package ecommerce.travel.utility.entity;

import lombok.Data;

@Data
public class Eventlog {

    private Integer id;

    private String msgId;

    private String sendTime;

    private String content;

    private String type;

}
