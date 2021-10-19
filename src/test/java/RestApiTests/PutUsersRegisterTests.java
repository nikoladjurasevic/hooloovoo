package RestApiTests;

import RestApi.ApiUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;


public class PutUsersRegisterTests extends RestApiBaseTest {

  @Test
  public void testCreateUser() {
    String userName = "dragan" + currentTime;
    Response response = registerUser("Dragan3", "Dragovanovic3", password , userName);
    int statusCode = response.getStatusCode();
    ResponseBody body = response.getBody();
    log.debug(body.asString());

    ResponseBody getUserBody = getUser(userName);
  }



}
