package com.azizi.bank.controller;

import com.azizi.bank.dto.PaymentDto;
import com.azizi.bank.service.PaymentService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public String pay(@RequestBody @Valid PaymentDto paymentDto) {
        return paymentService.pay(paymentDto);
    }

}
