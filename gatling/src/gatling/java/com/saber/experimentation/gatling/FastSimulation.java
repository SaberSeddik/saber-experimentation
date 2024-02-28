package com.saber.experimentation.gatling;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.time.Duration;

public class FastSimulation extends Simulation {
  private HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080");

  private ScenarioBuilder scenario = scenario("Call fast endpoint")
      .exec(http("Call fast endpoint").get("/fast"));

  {
    setUp(
      scenario.injectOpen(
        incrementUsersPerSec(20)
          .times(20)
          .eachLevelLasting(5)
          .separatedByRampsLasting(5)
          .startingFrom(20)
      )
    ).maxDuration(Duration.ofMinutes(1))
     .protocols(httpProtocol);
  }
}
