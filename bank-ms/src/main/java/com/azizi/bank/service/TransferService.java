package com.azizi.bank.service;

import com.azizi.bank.dto.TransferDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    @PreAuthorize("@opaClientImpl.allowTransfer(#transferDto)")
    public String transfer(TransferDto transferDto) {
        return "transferred";
    }

}
