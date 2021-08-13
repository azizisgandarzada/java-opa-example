package com.azizi.bank.service;

import com.azizi.bank.dto.PaymentDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @PreAuthorize("@opaClientImpl.allowPayment(#paymentDto)")
    public String pay(PaymentDto paymentDto) {
        return "paid";
    }

}
