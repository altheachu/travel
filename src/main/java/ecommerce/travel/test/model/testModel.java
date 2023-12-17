package ecommerce.travel.test.model;

import lombok.Data;

@Data
public class testModel {

    private String msg;

    public testModel(String msg){
        this.msg = msg;
    }

}
