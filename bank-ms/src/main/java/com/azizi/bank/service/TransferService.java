package com.azizi.bank.service;

import com.azizi.bank.dto.TransferDto;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    public String transfer(TransferDto transferDto) {
        return "transferred";
    }

}
