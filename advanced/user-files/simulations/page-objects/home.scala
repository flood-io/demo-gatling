import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Home {
  val visit = exec(http("Home")
    .get("/"))
    .pause(1)
}