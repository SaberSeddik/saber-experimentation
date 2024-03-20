package com.saber.experimentation.gatling.simulations;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.time.Duration;

public class WebFluxSimulation extends Simulation {
  private HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080");

  private ScenarioBuilder scenario = scenario("WebFlux behaviour with one slow api")
      .exec(http("Call fast endpoint").get("/fast"))
      .exec(http("Call slow endpoint").get("/slow-reactive"));

  {
    setUp(
      scenario.injectOpen(
        incrementUsersPerSec(50)
          .times(5)
          .eachLevelLasting(5)
          .separatedByRampsLasting(5)
          .startingFrom(50)
       )
    ).maxDuration(Duration.ofMinutes(1))
     .protocols(httpProtocol);
  }
}
