package com.azizi.bank.client;

import com.azizi.bank.dto.client.OpaDto;
import com.azizi.bank.dto.client.OpaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "opaClient", url = "${feign.client.config.opaClient.url}")
public interface OpaClient {

    @PostMapping("authz/allow")
    OpaResponse allow(@RequestBody OpaDto opaDto);

}
