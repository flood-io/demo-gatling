import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class LoadTest extends Simulation {
  val threads   = Integer.getInteger("threads",  5)
  val rampup    = Integer.getInteger("rampup",   10).toLong
  val duration  = Integer.getInteger("duration", 120).toLong

  val httpConf = http.baseURL("https://loadtest.flood.io")

  val myScenario = scenario("scenario")
    .during(duration seconds) {
      exec(Home.visit)
      .exec(Search.search)
      .exec(Authentication.login)
      .exec(Shopping.browse)
      .pause(1000 milliseconds, 5000 milliseconds)
    }

  setUp(myScenario.inject(rampUsers(threads) over (rampup seconds))).protocols(httpConf)
}
