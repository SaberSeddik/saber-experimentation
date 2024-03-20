package com.saber.experimentation.webmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.microsoft.applicationinsights.attach.ApplicationInsights;

@SpringBootApplication
public class WebApplication {

  public static void main(String[] args) {
    ApplicationInsights.attach();
    SpringApplication.run(WebApplication.class, args);
  }
}
