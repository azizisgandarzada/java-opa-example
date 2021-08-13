package com.azizi.bank.dto.client;

import com.azizi.bank.enums.Operation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InputDto {

    private Operation operation;
    private Object payload;

}
