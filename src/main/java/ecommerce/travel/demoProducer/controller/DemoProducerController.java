package ecommerce.travel.demoProducer.controller;

import ecommerce.travel.demoProducer.model.DemoModel;
import ecommerce.travel.demoProducer.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
@CrossOrigin(value = "http://localhost:8080")
@Api(tags = "Demo")
public class DemoProducerController {

    public DemoService demoService;
    public DemoProducerController(DemoService demoService){
        this.demoService = demoService;
    }


    @GetMapping
    @ApiOperation(value = "Get Demo Msg")
    public DemoModel getDemoMsg(){
        DemoModel model = new DemoModel("Hi");
        return model;
    }

    @PostMapping("/sendToExchange")
    @ApiOperation(value = "Send Msg To RabbitMq")
    public String sendToExchange(@RequestParam(name="msg") String msg) throws Exception {
        return demoService.sendMsg(msg);
    }

}
