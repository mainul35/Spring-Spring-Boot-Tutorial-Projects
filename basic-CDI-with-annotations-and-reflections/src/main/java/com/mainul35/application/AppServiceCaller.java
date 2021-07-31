package com.mainul35.application;

import com.mainul35.cdi.Autowired;
import com.mainul35.cdi.Component;

@Component
public class AppServiceCaller {

    @Autowired
    private AppService appService;

    @Autowired
    private AppService2 appService2;

    public void sayHello1() {
        appService.sayHello();
    }

    public void sayHello2() {
        appService2.sayHello();
    }


}
