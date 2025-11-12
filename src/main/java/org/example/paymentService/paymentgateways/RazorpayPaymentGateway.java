package org.example.paymentService.paymentgateways;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import org.example.paymentService.exception.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RazorpayPaymentGateway {
    @Autowired
    private RazorpayClient razorpayClient;
    public String getPaymentLink(String email,String name,String phoneNo,String orderId,Long amount) {
        try{
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", amount);
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("accept_partial", true);
            paymentLinkRequest.put("first_min_partial_amount", 100);
            paymentLinkRequest.put("expire_by", 1756913130);
            paymentLinkRequest.put("reference_id",orderId);
            paymentLinkRequest.put("description", "Payment for policy no #23456");
            JSONObject customer = new JSONObject();
            customer.put("name",phoneNo );
            customer.put("contact",name);
            customer.put("email",email);
            paymentLinkRequest.put("customer", customer);
            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);
            paymentLinkRequest.put("reminder_enable", true);
            JSONObject notes = new JSONObject();
            notes.put("policy_name", "Life Insurance Policy");
            paymentLinkRequest.put("notes", notes);
            paymentLinkRequest.put("callback_url", "https://example-callback-url.com/");
            paymentLinkRequest.put("callback_method", "get");

            PaymentLink payment = null;
            try {
                payment = razorpayClient.paymentLink.create(paymentLinkRequest);
            } catch (com.razorpay.RazorpayException e) {
                throw new RuntimeException(e);
            }
            return payment.get("short_url").toString();
        }catch(RazorpayException exception){
            throw new RuntimeException(exception.getMessage());
        }
    }
}
