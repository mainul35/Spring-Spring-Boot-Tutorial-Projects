package com.mainul35.application;

import com.mainul35.cdi.Component;

@Component
public class AppService {
    public void sayHello() {
        System.out.println("Say Hello from App Service");
    }
}
