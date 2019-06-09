package com.chao.storagebox;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.chao.storagebox.dao")
public class AccountbookApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(AccountbookApplication.class, args);
    }

}
