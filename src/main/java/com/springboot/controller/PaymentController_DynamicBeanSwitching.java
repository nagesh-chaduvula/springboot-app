package com.springboot.controller;

import com.springboot.dynamicbeanswithwithmap.dto.PaymentRequest;
import com.springboot.dynamicbeanswithwithmap.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/api/paymentWithDynamicBeanSwitch")
@RequiredArgsConstructor
@Slf4j
public class PaymentController_DynamicBeanSwitching {

    private final Map<String, PaymentService> paymentServiceMap;

    @PostMapping("/pay")
    public String pay(@RequestBody PaymentRequest paymentRequest) {
        final double amount = paymentRequest.getAmount();
        final String paymentMode = paymentRequest.getPaymentMode();
        final String sender = paymentRequest.getSender();
        final String receiver = paymentRequest.getReceiver();
        log.info("payment request: " + paymentRequest.toString());

        final PaymentService paymentService = paymentServiceMap.get(paymentMode);
        if (Objects.isNull(paymentService)) {
            throw new IllegalArgumentException("Unsupported payment mode: " + paymentMode);
        }

        return paymentService.pay(amount, paymentMode, sender, receiver);
    }
}
