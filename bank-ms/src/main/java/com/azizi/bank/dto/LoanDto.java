package com.azizi.bank.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class LoanDto {

    @NotBlank
    private String user;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    @Positive
    private Integer period;

}
