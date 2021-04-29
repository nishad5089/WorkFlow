package com.workflow.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
                System.out.println("===============================");

//        Set<Method> allMethods = new Reflections("com.workflow", new MethodAnnotationsScanner()).getMethodsAnnotatedWith(EnableWorkFlow.class);
//        for (Method m : allMethods){
//            if (m.isAnnotationPresent(EnableWorkFlow.class)) {
//                System.out.println(m.getName());
//            }
//        }
    }

}
