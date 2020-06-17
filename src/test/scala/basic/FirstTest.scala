package basic


import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._


class FirstTest extends Simulation {

  //header
  val headers = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
    "Accept-Encoding" -> "gzip, deflate",
    "Accept-Language" -> "en-GB,en-US;q=0.9,en;q=0.8",
    "User-Agent" -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36"
  )

  //http protocol
  val httpProtocol = http
    .baseUrl("http://newtours.demoaut.com/")
    .headers(headers)

  //scenario
  val scenario_1 = scenario(scenarioName = "viewCruise")
    .exec(http("request_1").get("/mercurycruise.php"))
    .pause(10)

  setUp(scenario_1.inject(atOnceUsers(2))).protocols(httpProtocol)
}
