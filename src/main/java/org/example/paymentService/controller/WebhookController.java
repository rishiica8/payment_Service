package org.example.paymentService.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {
    @PostMapping("/razorpayWebhook")
    public String receiveEvents(@RequestBody String params){
          System.out.println(params);
          return params;
    }

}
