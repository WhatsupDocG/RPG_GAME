package com.util;

import org.springframework.stereotype.Service;

@Service
public class CallbackServiceImpl implements CallBackService {
    @Override
    public void onSecondMicroserviceProcessed(String result) {
        // Обработка уведомления о завершении второго микросервиса
        System.out.println("Second microservice processed the request. Result: " + result);
    }
}
