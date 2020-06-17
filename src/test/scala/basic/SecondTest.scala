package basic

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._


class SecondTest extends Simulation {

  val httpProtocol = http
    .baseUrl("https://cheeze-flight-booker.herokuapp.com")
    .inferHtmlResources()
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")

  val headers_0 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "en-US,en;q=0.9",
    "Upgrade-Insecure-Requests" -> "1")

  val headers_1 = Map(
    "Accept" -> "*/*",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "en-US,en;q=0.9")

  val headers_2 = Map(
    "Accept" -> "image/webp,image/apng,image/*,*/*;q=0.8",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "en-US,en;q=0.9",
    "Pragma" -> "no-cache")

  val headers_11 = Map(
    "Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
    "Accept-Encoding" -> "gzip, deflate, br",
    "Accept-Language" -> "en-US,en;q=0.9",
    "Origin" -> "https://cheeze-flight-booker.herokuapp.com",
    "Upgrade-Insecure-Requests" -> "1")


  val scn = scenario("SecondTest")

    .exec(http("Homepage")						// 'request_0' renamed to 'Homepage'
      .get("/")
      .headers(headers_0)
      .resources(http("request_1")
        .get("/assets/application-2534172286055efef05dbb34d2da8fc2.js")
        .headers(headers_1)))
    .pause(1)
    .exec(http("request_2")
      .get("/favicon.ico")
      .headers(headers_2))
    .pause(44)

    .exec(http("SearchFlight")						// 'request_3' renamed to 'SearchFlight'
      .get("/flights?utf8=%E2%9C%93&from=1&to=2&date=2015-01-03&num_passengers=2&commit=search")
      .headers(headers_0)
      .resources(http("request_4")
        .get("/assets/application-2534172286055efef05dbb34d2da8fc2.js")
        .headers(headers_1),
        http("request_5")
          .get("/assets/application-c99cbb3caf78d16bb1482ca2e41d7a9c.css")))
    .pause(1)
    .exec(http("request_6")
      .get("/favicon.ico"))
    .pause(29)

    .exec(http("SelectFlight")						// 'request_7' renamed to 'SelectFlight'
      .get("/bookings/new?utf8=%E2%9C%93&flight_id=10&commit=Select+Flight&num_passengers=2")
      .headers(headers_0)
      .resources(http("request_8")
        .get("/assets/application-2534172286055efef05dbb34d2da8fc2.js")
        .headers(headers_1),
        http("request_9")
          .get("/assets/application-c99cbb3caf78d16bb1482ca2e41d7a9c.css")))
    .pause(1)
    .exec(http("request_10")
      .get("/favicon.ico"))
    .pause(85)

    .exec(http("BookFlight")						// 'request_11' renamed to 'BookFlight'
      .post("/bookings")
      .headers(headers_11)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "y5BbEyinmIG2AmdPPL+tQzyFu4MpAi9oJZSA8pCGNoLGhrnXj5tRicBpFCGFOonY30qYw0egHCFoV9aAfOeaSw==")
      .formParam("booking[flight_id]", "10")
      .formParam("booking[passengers_attributes][0][name]", "Andrew")
      .formParam("booking[passengers_attributes][0][email]", "andrew654@ggmail.com")
      .formParam("booking[passengers_attributes][1][name]", "Scott")
      .formParam("booking[passengers_attributes][1][email]", "scott654@ggmail.com")
      .formParam("commit", "Book Flight"))
    .pause(1)
    .exec(http("request_12")
      .get("/favicon.ico"))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)

}
