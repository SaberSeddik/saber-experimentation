package com.saber.experimentation.gatling.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

  @RequestMapping("fast")
  public String fast(){
    return "fast";
  }
}
