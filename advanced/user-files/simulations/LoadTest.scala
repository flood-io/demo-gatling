import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class LoadTest extends Simulation {
  val threads   = Integer.getInteger("threads",  5)
  val rampup    = java.lang.Long.getLong("rampup", 10L)
  val duration  = java.lang.Long.getLong("duration", 30L)

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
