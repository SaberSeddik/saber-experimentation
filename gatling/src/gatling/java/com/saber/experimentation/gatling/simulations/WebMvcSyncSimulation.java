package com.saber.experimentation.gatling.simulations;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import java.time.Duration;

public class WebMvcSyncSimulation extends Simulation {
  private HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8090");

  private ScenarioBuilder scenarioFast = scenario("WebMvc behaviour with one fast api")
      .exec(http("Call fast endpoint").get("/fast"));
  private ScenarioBuilder scenarioSlow = scenario("WebMvc behaviour with one slow api")
      .exec(http("Call slow endpoint").get("/slow-sync"));

  {
    setUp(
      scenarioFast.injectOpen(
        constantUsersPerSec(200).during(60)
       ),
       scenarioSlow.injectOpen(
        constantUsersPerSec(200).during(60)
       )
    ).maxDuration(Duration.ofMinutes(1))
     .protocols(httpProtocol);
  }
}
