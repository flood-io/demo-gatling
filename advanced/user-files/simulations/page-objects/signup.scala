import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Signup {
  val newUser = exec(http("Signup")
    .get("/random"))
    .pause(1)
    .exec(http("SignupPost")
    .post("/slow"))
}