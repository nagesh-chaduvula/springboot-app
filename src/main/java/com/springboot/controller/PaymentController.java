package com.springboot.controller;

import com.springboot.dynamicbeanswithwithmap.dto.PaymentRequest;
import com.springboot.dynamicbeanswithwithmap.service.impl.PaypalPaymentServiceImpl;
import com.springboot.dynamicbeanswithwithmap.service.impl.RazorpayPaymentServiceImpl;
import com.springboot.dynamicbeanswithwithmap.service.impl.StripePaymentServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private static final String PAYPAL = "paypal";
    private static final String RAZORPAY = "razorpay";
    private static final String STRIPE = "stripe";

    private final PaypalPaymentServiceImpl paypalPaymentService;
    private final RazorpayPaymentServiceImpl razorpayPaymentService;
    private final StripePaymentServiceImpl stripePaymentService;

    public PaymentController(PaypalPaymentServiceImpl paypalPaymentService, RazorpayPaymentServiceImpl razorpayPaymentService, StripePaymentServiceImpl stripePaymentService) {
        this.paypalPaymentService = paypalPaymentService;
        this.razorpayPaymentService = razorpayPaymentService;
        this.stripePaymentService = stripePaymentService;
    }

    @PostMapping("/pay")
    public String pay(@RequestBody PaymentRequest paymentRequest) {
        final double amount = paymentRequest.getAmount();
        final String paymentMode = paymentRequest.getPaymentMode();
        final String sender = paymentRequest.getSender();
        final String receiver = paymentRequest.getReceiver();

        return switch (paymentMode.toLowerCase()) {
            case PAYPAL -> paypalPaymentService.pay(amount, paymentMode, sender, receiver);
            case RAZORPAY -> razorpayPaymentService.pay(amount, paymentMode, sender, receiver);
            case STRIPE -> stripePaymentService.pay(amount, paymentMode, sender, receiver);
            default -> throw new IllegalArgumentException("Unsupported payment mode: " + paymentMode);
        };

    }
}
