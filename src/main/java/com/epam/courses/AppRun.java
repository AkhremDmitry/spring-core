package com.epam.courses;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppRun {
    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx =
                new ClassPathXmlApplicationContext("spring.xml");

        SomeService service = appCtx.getBean(SomeService.class);
        service.call();

        appCtx.close();
    }
}
