package com.azizi.bank.controller;

import com.azizi.bank.dto.LoanDto;
import com.azizi.bank.service.LoanService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public String order(@RequestBody @Valid LoanDto loanDto) {
        return loanService.order(loanDto);
    }

}
