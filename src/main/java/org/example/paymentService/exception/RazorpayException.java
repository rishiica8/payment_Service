package org.example.paymentService.exception;

public class RazorpayException extends RuntimeException{
    public RazorpayException(String message) {
        super(message);
    }
}
