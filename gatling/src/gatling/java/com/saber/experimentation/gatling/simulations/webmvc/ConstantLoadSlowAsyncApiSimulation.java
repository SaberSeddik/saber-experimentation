package com.saber.experimentation.gatling.simulations.webmvc;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.time.Duration;

public class ConstantLoadSlowAsyncApiSimulation extends Simulation {
  private HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8090");

  private ScenarioBuilder scenarioFast = scenario("WebMvc behaviour with one fast api")
      .exec(http("Call fast endpoint").get("/fast"));
  private ScenarioBuilder scenarioSlowAsync = scenario("WebMvc behaviour with one slow async api")
      .exec(http("Call slow async endpoint").get("/slow-async"));

  {
    setUp(
      scenarioSlowAsync.injectOpen(
        constantUsersPerSec(200).during(Duration.ofSeconds(60))),
      scenarioFast.injectOpen(
        constantUsersPerSec(200).during(Duration.ofSeconds(60)))
    ).protocols(httpProtocol);
  }    
}
