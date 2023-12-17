package ecommerce.travel.test.controller;

import ecommerce.travel.test.model.testModel;
import ecommerce.travel.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@CrossOrigin(value = "http://localhost:8080")
public class testController {

    @Autowired
    public TestService testService;

    @GetMapping
    public testModel demoCtrlMthd(){
        testModel model = new testModel("Hi");
        return model;
    }

    @PostMapping("/sendToExchange")
    public String sendToExchange(@RequestParam(name="msg") String msg) throws Exception {
        return testService.sendMsg(msg);
    }

}
