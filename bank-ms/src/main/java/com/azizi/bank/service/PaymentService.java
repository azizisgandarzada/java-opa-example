package com.azizi.bank.service;

import com.azizi.bank.dto.PaymentDto;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public String pay(PaymentDto paymentDto) {
        return "paid";
    }

}
