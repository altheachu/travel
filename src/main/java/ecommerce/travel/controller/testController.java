package ecommerce.travel.controller;

import ecommerce.travel.model.testModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@CrossOrigin(value = "http://localhost:8080")
public class testController {

    @GetMapping
    public testModel demoCtrlMthd(){
        testModel model = new testModel("Hi");
        return model;
    }

}
