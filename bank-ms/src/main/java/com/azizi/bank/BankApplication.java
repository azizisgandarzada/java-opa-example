package com.azizi.bank;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BankApplication {

    public static void main(String[] args) {
        run(BankApplication.class, args);
    }

}
