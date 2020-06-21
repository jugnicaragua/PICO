package org.ni.jug.pico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PicoCoreApplication {

    static ApplicationContext applicationContext;

    @Autowired
    void setApplicationContext(ApplicationContext applicationContext) {
        PicoCoreApplication.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> beanType) {
        return applicationContext.getBean(beanType);
    }

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(PicoCoreApplication.class, args);
    }
}
