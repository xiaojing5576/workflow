package com.jing.workflow;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableProcessApplication
@SpringBootApplication
public class WorkflowApplication {

    public static void main(String[] args ) {
        SpringApplication.run(WorkflowApplication.class, args);
    }

}
