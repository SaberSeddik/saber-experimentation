package com.saber.experimentation.gatling.simulations.webflux;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.time.Duration;

public class ConstantLoadFastApiSimulation extends Simulation {
  private HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080");

  private ScenarioBuilder scenarioFast = scenario("WebMvc behaviour with one fast api")
      .exec(http("Call fast endpoint").get("/fast"));
  private ScenarioBuilder scenarioSlowSync = scenario("WebMvc behaviour with one slow sync api")
      .exec(http("Call slow sync endpoint").get("/slow-sync"));

  {
    setUp(
      scenarioSlowSync.injectOpen(
        constantUsersPerSec(200).during(Duration.ofSeconds(60))),
      scenarioFast.injectOpen(
        constantUsersPerSec(200).during(Duration.ofSeconds(60)))
    ).protocols(httpProtocol);
  }    
}
