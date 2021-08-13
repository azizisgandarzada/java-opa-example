package com.azizi.bank.aspectj.controller;

import com.azizi.bank.client.OpaClient;
import com.azizi.bank.dto.PaymentDto;
import com.azizi.bank.dto.client.InputDto;
import com.azizi.bank.dto.client.OpaDto;
import com.azizi.bank.dto.client.OpaResponse;
import com.azizi.bank.enums.Operation;
import com.azizi.bank.exception.AccessDeniedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class PaymentControllerAspect {

    private final OpaClient opaClient;

    @Before("execution(* com.azizi.bank.controller.PaymentController.pay(..)) && args(paymentDto)")
    public void pay(PaymentDto paymentDto) {
        log.info("Payment Dto {}", paymentDto);
        InputDto input = InputDto.builder()
                .operation(Operation.PAYMENT)
                .payload(paymentDto)
                .build();
        OpaDto opaDto = OpaDto.builder()
                .input(input)
                .build();
        OpaResponse result = opaClient.allowPayment(opaDto);
        if (!result.isResult()) {
            throw new AccessDeniedException();
        }
    }

}
