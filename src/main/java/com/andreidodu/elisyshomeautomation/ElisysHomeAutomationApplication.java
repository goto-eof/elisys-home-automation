package com.andreidodu.elisyshomeautomation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ElisysHomeAutomationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElisysHomeAutomationApplication.class, args);
    }

}
