package com.azizi.bank.service;

import com.azizi.bank.dto.LoanDto;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    public String order(LoanDto loanDto) {
        return "ordered";
    }

}
