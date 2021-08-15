package com.azizi.bank.client.impl;

import com.azizi.bank.client.OpaClient;
import com.azizi.bank.dto.LoanDto;
import com.azizi.bank.dto.PaymentDto;
import com.azizi.bank.dto.TransferDto;
import com.azizi.bank.dto.client.InputDto;
import com.azizi.bank.dto.client.OpaDto;
import com.azizi.bank.dto.client.OpaResponse;
import com.azizi.bank.enums.Operation;
import com.azizi.bank.exception.AccessDeniedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpaClientImpl {

    private final OpaClient opaClient;

    public boolean allow(InputDto inputDto) {
        if (!isAuthenticated()) {
            return false;
        }
        OpaDto opaDto = OpaDto.builder()
                .input(inputDto)
                .build();
        OpaResponse response = opaClient.allowPayment(opaDto);
        return checkResult(response);
    }


    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    private boolean checkResult(OpaResponse response) {
        if (!response.isResult()) {
            throw new AccessDeniedException();
        }
        return true;
    }

}
