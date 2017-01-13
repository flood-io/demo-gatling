import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Authentication {
  val login = exec(http("Login")
    .get("/random"))
    .pause(1)
    .exec(http("LoginPost")
    .post("/slow"))
}