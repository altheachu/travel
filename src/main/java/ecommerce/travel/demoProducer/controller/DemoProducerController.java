package ecommerce.travel.demoProducer.controller;

import ecommerce.travel.demoProducer.model.DemoModel;
import ecommerce.travel.demoProducer.service.DemoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
@CrossOrigin(value = "http://localhost:8080")
public class DemoProducerController {

    public DemoService demoService;
    public DemoProducerController(DemoService demoService){
        this.demoService = demoService;
    }

    @GetMapping
    public DemoModel getDemoMsg(){
        DemoModel model = new DemoModel("Hi");
        return model;
    }

    @PostMapping("/sendToExchange")
    public String sendToExchange(@RequestParam(name="msg") String msg) throws Exception {
        return demoService.sendMsg(msg);
    }

}
