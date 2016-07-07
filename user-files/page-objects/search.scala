import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Search {
  val feeder = csv("search.csv").random

  val search = exec(http("Search")
    .get("/"))
    .pause(1)
    .feed(feeder)
    .exec(http("SearchPost")
    .post("/random?f=${searchCriterion}"))
}