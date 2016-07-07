import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Shopping {
  val feeder = csv("search.csv").random

  val browse = exec(http("Browse")
    .get("/"))
    .pause(1)
    .feed(feeder)
    .exec(http("AddItem")
    .post("/random?f=${searchCriterion}"))
}