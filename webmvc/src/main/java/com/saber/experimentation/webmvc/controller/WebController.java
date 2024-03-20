package com.saber.experimentation.webmvc.controller;

import java.util.concurrent.Callable;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class WebController {

  @RequestMapping("fast")
  public String fast(){
    log.info("Controller request for fast");
    return "fast";
  }

  @RequestMapping("medium")
  public String medium(){
    log.info("Controller request for medium");
    sleep(50);
    return "medium";
  }

  @RequestMapping("slow-sync")
  public String slowSync(){
    log.info("Controller request for slow sync");
    return slowString();
  }

  @Async
  @RequestMapping("slow-async")
  public Callable<String> slowAsync(){
    log.info("Controller request for slow async");
    return WebController::slowString;
  }

  private static String slowString() {
    log.info("Slow before sleep");
    sleep(3 * 1000);
    log.info("Deffered String");
    return "slow";
  }

  private static void sleep(long millis){
    try {
      log.info("Starting long-running task...");
      Thread.sleep(millis);
      log.info("Task completed!");
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
  }
}
