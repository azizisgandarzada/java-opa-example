package com.azizi.bank.service;

import com.azizi.bank.dto.LoanDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    @PreAuthorize("@opaClientImpl.allowLoanOrder(#loanDto)")
    public String order(LoanDto loanDto) {
        return "ordered";
    }

}
