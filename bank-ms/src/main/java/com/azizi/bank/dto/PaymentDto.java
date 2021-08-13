package com.azizi.bank.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaymentDto {

    @NotBlank
    private String user;

    @NotBlank
    private String merchant;

    @NotNull
    @Positive
    private BigDecimal amount;


}
