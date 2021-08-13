package com.azizi.bank.client;

import com.azizi.bank.dto.client.OpaDto;
import com.azizi.bank.dto.client.OpaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "opaClient", url = "${feign.client.config.opaClient.url}")
public interface OpaClient {

    @PostMapping("authz/allow_payment")
    OpaResponse allowPayment(@RequestBody OpaDto opaDto);

    @PostMapping("authz/allow_transfer")
    OpaResponse allowTransfer(@RequestBody OpaDto opaDto);

    @PostMapping("authz/allow_loan_order")
    OpaResponse allowLoanOrder(@RequestBody OpaDto opaDto);

}
