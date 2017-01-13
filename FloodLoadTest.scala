import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

// Mandatory, you must import Flood libraries
import flood._

// We upload this simulation separately to the user-files.zip
// in this advanced example so that Flood IO can parse the
// simulation name to execute.
class FloodLoadTest extends Simulation {
  // Optional, Flood IO will pass in threads, rampup and duration properties from UI
  val threads   = Integer.getInteger("threads",  100)
  val rampup    = java.lang.Long.getLong("rampup", 30L)
  val duration  = java.lang.Long.getLong("duration", 300L)

  // Mandatory, you must use httpConfigFlood
  val httpConf = httpConfigFlood.baseURL("https://loadtest.flood.io")

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
