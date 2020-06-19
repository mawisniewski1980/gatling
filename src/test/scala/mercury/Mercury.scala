package mercury

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class Mercury extends Simulation {

	val httpProtocol = http
		.baseUrl("http://newtours.demoaut.com")
		.inferHtmlResources()
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36")

	val headers_18 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
		"Accept-Encoding" -> "gzip, deflate",
		"Accept-Language" -> "en-GB,en-US;q=0.9,en;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_19 = Map(
		"Accept" -> "image/webp,image/apng,image/*,*/*;q=0.8",
		"Accept-Encoding" -> "gzip, deflate",
		"Accept-Language" -> "en-GB,en-US;q=0.9,en;q=0.8")

	val headers_21 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
		"Accept-Encoding" -> "gzip, deflate",
		"Accept-Language" -> "en-GB,en-US;q=0.9,en;q=0.8",
		"Origin" -> "http://newtours.demoaut.com",
		"Upgrade-Insecure-Requests" -> "1")



	val scn = scenario("Mercury")
		.exec(http("request_0")
			.get("/images/nav/logo.gif")
			.resources(http("request_1")
			.get("/images/nav/html.gif"),
            http("request_2")
			.get("/images/nav/boxad1.gif"),
            http("request_3")
			.get("/images/banner2.gif"),
            http("request_4")
			.get("/images/featured_destination.gif"),
            http("request_5")
			.get("/images/hdr_specials.gif"),
            http("request_6")
			.get("/images/hdr_tips.gif"),
            http("request_7")
			.get("/images/tip93.gif"),
            http("request_8")
			.get("/images/hdr_findflight.gif"),
            http("request_9")
			.get("/images/hdr_right.gif"),
            http("request_10")
			.get("/images/btn_signin.gif"),
            http("request_11")
			.get("/images/hdr_destinations.gif"),
            http("request_12")
			.get("/images/icn_destinations.gif"),
            http("request_13")
			.get("/images/hdr_vacation.gif"),
            http("request_14")
			.get("/images/icn_vacations.gif"),
            http("request_15")
			.get("/images/hdr_register.gif"),
            http("request_16")
			.get("/images/icn_register.gif"),
            http("request_17")
			.get("/images/hdr_links.gif"),
            http("request_18")
			.get("/mercurywelcome.php")
			.headers(headers_18),
            http("request_19")
			.get("/images/spacer.gif")
			.headers(headers_19)
			.check(status.is(404)),
            http("request_20")
			.get("/black")
			.headers(headers_19)
			.check(status.is(404))))
		.pause(7)
		.exec(http("request_21")
			.post("/login.php")
			.headers(headers_21)
			.formParam("action", "process")
			.formParam("userName", "tutorial")
			.formParam("password", "tutorial")
			.formParam("login.x", "35")
			.formParam("login.y", "7")
			.resources(http("request_22")
			.get("/images/nav/html.gif"),
            http("request_23")
			.get("/images/nav/boxad1.gif"),
            http("request_24")
			.get("/images/banner2.gif"),
            http("request_25")
			.get("/images/masts/mast_flightfinder.gif"),
            http("request_26")
			.get("/images/spacer.gif")
			.headers(headers_19)
			.check(status.is(404)),
            http("request_27")
			.get("/mercuryreservation.php")
			.headers(headers_18),
            http("request_28")
			.get("/images/forms/continue.gif"),
            http("request_29")
			.get("/images/spacer.gif")
			.headers(headers_19)
			.check(status.is(404))))
		.pause(11)
		.exec(http("request_30")
			.get("/images/nav/logo.gif")
			.resources(http("request_31")
			.get("/images/nav/html.gif"),
            http("request_32")
			.get("/images/nav/boxad1.gif"),
            http("request_33")
			.get("/images/banner2.gif"),
            http("request_34")
			.get("/images/masts/mast_selectflight.gif"),
            http("request_35")
			.get("/images/spacer.gif")
			.headers(headers_19)
			.check(status.is(404)),
            http("request_36")
			.post("/mercuryreservation2.php")
			.headers(headers_21)
			.formParam("tripType", "roundtrip")
			.formParam("passCount", "1")
			.formParam("fromPort", "Frankfurt")
			.formParam("fromMonth", "7")
			.formParam("fromDay", "19")
			.formParam("toPort", "New York")
			.formParam("toMonth", "8")
			.formParam("toDay", "19")
			.formParam("servClass", "Coach")
			.formParam("airline", "No Preference")
			.formParam("findFlights.x", "44")
			.formParam("findFlights.y", "11"),
            http("request_37")
			.get("/images/forms/continue.gif")))
		.pause(2)
		.exec(http("request_38")
			.get("/images/nav/logo.gif")
			.resources(http("request_39")
			.get("/images/nav/html.gif"),
            http("request_40")
			.get("/images/nav/boxad1.gif"),
            http("request_41")
			.get("/images/banner2.gif"),
            http("request_42")
			.get("/images/masts/mast_book.gif"),
            http("request_43")
			.get("/images/spacer.gif")
			.headers(headers_19)
			.check(status.is(404)),
            http("request_44")
			.post("/mercurypurchase.php")
			.headers(headers_21)
			.formParam("fromPort", "Frankfurt")
			.formParam("toPort", "New York")
			.formParam("passCount", "1")
			.formParam("toDay", "19")
			.formParam("toMonth", "8")
			.formParam("fromDay", "19")
			.formParam("fromMonth", "7")
			.formParam("servClass", "Coach")
			.formParam("outFlight", "Blue Skies Airlines$360$270$5:03")
			.formParam("inFlight", "Blue Skies Airlines$630$270$12:23")
			.formParam("reserveFlights.x", "42")
			.formParam("reserveFlights.y", "15"),
            http("request_45")
			.get("/images/forms/purchase.gif"),
            http("request_46")
			.get("/black")
			.headers(headers_19)
			.check(status.is(404)),
            http("request_47")
			.get("/images/spacer.gif")
			.headers(headers_19)
			.check(status.is(404))))
		.pause(9)
		.exec(http("request_48")
			.get("/images/nav/logo.gif")
			.resources(http("request_49")
			.get("/images/nav/html.gif"),
            http("request_50")
			.get("/images/nav/boxad1.gif"),
            http("request_51")
			.get("/images/banner2.gif"),
            http("request_52")
			.get("/images/masts/mast_confirmation.gif"),
            http("request_53")
			.get("/images/printit.gif"),
            http("request_54")
			.post("/mercurypurchase2.php")
			.headers(headers_21)
			.formParam("outFlightName", "Blue Skies Airlines")
			.formParam("outFlightNumber", "360")
			.formParam("outFlightPrice", "270")
			.formParam("outFlightTime", "5:03")
			.formParam("inFlightName", "Blue Skies Airlines")
			.formParam("inFlightNumber", "630")
			.formParam("inFlightPrice", "270")
			.formParam("inFlightTime", "12:23")
			.formParam("fromPort", "Frankfurt")
			.formParam("toPort", "New York")
			.formParam("passCount", "1")
			.formParam("toDay", "19")
			.formParam("toMonth", "8")
			.formParam("fromDay", "19")
			.formParam("fromMonth", "7")
			.formParam("servClass", "Coach")
			.formParam("subtotal", "540")
			.formParam("taxes", "44")
			.formParam("passFirst0", "Max")
			.formParam("passLast0", "Kolnko")
			.formParam("pass.0.meal", "")
			.formParam("creditCard", "AX")
			.formParam("creditnumber", "411111111111")
			.formParam("cc_exp_dt_mn", "None")
			.formParam("cc_exp_dt_yr", "None")
			.formParam("cc_frst_name", "")
			.formParam("cc_mid_name", "")
			.formParam("cc_last_name", "")
			.formParam("billAddress1", "1325 Borregas Ave.")
			.formParam("billAddress2", "")
			.formParam("billCity", "Sunnyvale")
			.formParam("billState", "CA")
			.formParam("billZip", "94089")
			.formParam("billCountry", "215")
			.formParam("delAddress1", "1325 Borregas Ave.")
			.formParam("delAddress2", "")
			.formParam("delCity", "Sunnyvale")
			.formParam("delState", "CA")
			.formParam("delZip", "94089")
			.formParam("delCountry", "215")
			.formParam("buyFlights.x", "67")
			.formParam("buyFlights.y", "5"),
            http("request_55")
			.get("/images/forms/backtoflights.gif"),
            http("request_56")
			.get("/images/forms/home.gif"),
            http("request_57")
			.get("/images/forms/Logout.gif"),
            http("request_58")
			.get("/images/spacer.gif")
			.headers(headers_19)
			.check(status.is(404)),
            http("request_59")
			.get("/black")
			.headers(headers_19)
			.check(status.is(404))))
		.pause(3)
		.exec(http("request_60")
			.get("/mercurysignoff.php")
			.headers(headers_18)
			.resources(http("request_61")
			.get("/images/nav/html.gif"),
            http("request_62")
			.get("/images/nav/boxad1.gif"),
            http("request_63")
			.get("/images/banner2.gif"),
            http("request_64")
			.get("/images/masts/mast_signon.gif"),
            http("request_65")
			.get("/images/forms/submit.gif"),
            http("request_66")
			.get("/mercurysignon.php")
			.headers(headers_18),
            http("request_67")
			.get("/black")
			.headers(headers_19)
			.check(status.is(404)),
            http("request_68")
			.get("/images/spacer.gif")
			.headers(headers_19)
			.check(status.is(404))))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}