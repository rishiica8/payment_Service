package org.example.paymentService.service;

import org.example.paymentService.paymentgateways.IPaymentGateway;
import org.example.paymentService.paymentgateways.RazorpayPaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService{
    @Autowired
    private IPaymentGateway paymentGateway;
    @Override
    public String initiatePayment(String email,String name,String phoneNo,String orderId,Long amount){
        return paymentGateway.getPaymentLink(email,name,phoneNo,orderId,amount);
    }
}
