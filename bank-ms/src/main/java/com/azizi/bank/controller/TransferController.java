package com.azizi.bank.controller;

import com.azizi.bank.dto.TransferDto;
import com.azizi.bank.service.TransferService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public String transfer(@RequestBody @Valid TransferDto transferDto) {
        return transferService.transfer(transferDto);
    }

}
