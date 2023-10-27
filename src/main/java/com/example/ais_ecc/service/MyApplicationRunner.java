package com.example.ais_ecc.service;

import com.example.ais_ecc.service.DbInit;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private final DbInit dbInit;

    public MyApplicationRunner(DbInit dbInit) {
        this.dbInit = dbInit;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dbInit.Init(); // Выполнение инициализации приложения
    }
}
