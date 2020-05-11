package com.nancy.petshow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author chen
 * @date 2020/5/11 10:46
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableSwagger2
public class PetShowApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetShowApplication.class, args);
    }

}
