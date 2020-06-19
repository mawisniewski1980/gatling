package basic

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

import scala.util.Random


class SecondTest extends Simulation {

  val httpProtocol = http
    .baseUrl("https://cheeze-flight-booker.herokuapp.com")
    .inferHtmlResources()
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")

    //global silent resources - hide them on our raport
    .silentResources

    //silent specific request
    //.silentUri(".*bookings")

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

  //load data from file into memory
  val csvFeeder = csv("data/PassengersInfo.csv")
  //val csvFeeder1 = csv("data/PassengersInfo.csv").batch(200)
  //random feeder
  val randomEmailFeeder = Iterator.continually(Map("randomEmail" -> (Random.alphanumeric.take(20).mkString + "@ggmail.com")))

  val reqHomePage = exec(http("Homepage")						// 'request_0' renamed to 'Homepage'
    .get("/")
    .headers(headers_0)
    .resources(http("request_1")
      .get("/assets/application-2534172286055efef05dbb34d2da8fc2.js")
      .headers(headers_1)

      // global silent can be turn off for specific request
      // .notSilent
    )
    // to check status
    .check(status.in(200,201,202,304))
    .check(status.not(404)))

  val scn = scenario("SecondTest")

    //clear cache
    .exec(flushHttpCache)
    .exec(flushSessionCookies)
    .exec(flushCookieJar)

    //loops
    .repeat(2)
    {
      reqHomePage
    }

    .during(60) {
      reqHomePage
    }

    .forever {
      pause(1)
    }

    .pause(1)
    .exec(http("request_2")
      .get("/favicon.ico")
      .headers(headers_2)
      //silent resource in specific request
      .silent
    )
    .pause(10)

    .exec(http("SearchFlight")						// 'request_3' renamed to 'SearchFlight'
      .get("/flights?utf8=%E2%9C%93&from=1&to=2&date=2015-01-03&num_passengers=2&commit=search")
      .headers(headers_0)
      .resources(http("request_4")
        .get("/assets/application-2534172286055efef05dbb34d2da8fc2.js")
        .headers(headers_1),
        http("request_5")
          .get("/assets/application-c99cbb3caf78d16bb1482ca2e41d7a9c.css"))
          .check(currentLocationRegex(".num_passengers=2.*")))
    .pause(1)
    .exec(http("request_6")
      .get("/favicon.ico")
      //silent resource in specific request
      .silent)
    .pause(10)

    .exec(http("SelectFlight")						// 'request_7' renamed to 'SelectFlight'
      .get("/bookings/new?utf8=%E2%9C%93&flight_id=10&commit=Select+Flight&num_passengers=2")
      .headers(headers_0)
      .resources(http("request_8")
        .get("/assets/application-2534172286055efef05dbb34d2da8fc2.js")
        .headers(headers_1),
        http("request_9")
          .get("/assets/application-c99cbb3caf78d16bb1482ca2e41d7a9c.css"))
        .check(css("h1:contains('Book Flight')").exists)
        .check(substring("Email").find.exists)
        .check(substring("Email"))
        .check(substring("Email").count.is(2))
        //save token
        .check(css("input[name='authenticity_token']", "value").saveAs("authToken"))
        //save response body
        .check(bodyString.saveAs("BODY")))
          .exec {
           session =>
             println("TOKEN: ${authToken}")
             println(session("BODY: ").as[String])
           session
          }
    .pause(1)
    .exec(http("request_10")
      .get("/favicon.ico")
      //silent resource in specific request
      .silent)
    .pause(10)

    //get from file csv 2 names and 2 emails
    .feed(csvFeeder,2)
    .feed(randomEmailFeeder,2)
    .exec(http("BookFlight")						// 'request_11' renamed to 'BookFlight'
      .post("/bookings")
      .headers(headers_11)
      .formParam("utf8", "✓")
      .formParam("authenticity_token", "${authToken}")
      .formParam("booking[flight_id]", "10")
      .formParam("booking[passengers_attributes][0][name]", "${name1}")
      .formParam("booking[passengers_attributes][0][email]", "${randomEmail1}")
      .formParam("booking[passengers_attributes][1][name]", "${name2}")
      .formParam("booking[passengers_attributes][1][email]", "${randomEmail2}")
      .formParam("commit", "Book Flight"))
    .pause(1)
    .exec(http("request_12")
      .get("/favicon.ico")
      //silent resource in specific request
      .silent)

  //fixed pause
  //    .pause(10) //10 seconds

  // random pause
  //    .pause(10, 20)

  //force parameter
  //   .pause(10, constantPauses)
  //    .pause(10, uniformPausesPlusOrMinusDuration(5))

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
    .assertions(
      global.responseTime.max.lt(1000),
      forAll.responseTime.max.lt(1000),
      details("BookFlight").responseTime.max.lt(1000),
      global.successfulRequests.percent.is(100)
    )

  //global pauses
  //  .disablePauses
  //  .uniformPauses(10)
  //  .uniformPauses(10.00) // %
  //  .exponentialPauses
  //  .constantPauses


}
