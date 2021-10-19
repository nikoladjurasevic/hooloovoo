package RestApiTests;

import java.util.Date;

import RestApi.ApiUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class RestApiBaseTest extends ApiUtils{

  //Generating data for user info
  Date date = new Date();
  long currentTime = date.getTime();
  static final String password = "password";

  public RequestSpecification setUpRequest(boolean hasRequestBody) {
    RestAssured.baseURI = ApiUtils.BASE_URL;
    RequestSpecification request = RestAssured.given();
    if(hasRequestBody) { request.header("Content-Type", "application/json");};
    return request;
  }

  public JSONObject createUserRequestJSONObject(String firstName, String lastName, String password, String username) {
    JSONObject userObject = new JSONObject();
    userObject.put("firstName", firstName);
    userObject.put("lastName", lastName);
    userObject.put("password", password);
    userObject.put("username", username);
    return  userObject;
  }

  public Response registerUser(String firstName, String lastName, String password, String username) {
    log.debug("registerUser(" + firstName + ", " + lastName + ", "+ password + ", "+ username + ")");
    RequestSpecification request = setUpRequest(true);
    JSONObject userRequestBody = createUserRequestJSONObject(firstName, lastName, password , username);
    request.body(userRequestBody.toString());
    Response response = request.request(Method.PUT, ApiUtils.REGISTER_URL);
    return response;
  }

  public ResponseBody getUser(String username) {
    log.debug("getUser(" + username + ")");
    Response response = setUpRequest(false).request(Method.GET, ApiUtils.USERS_URL + "/" + username);
    ResponseBody responseBody = response.getBody();
    log.debug(responseBody.asString());
    return responseBody;
  }

}
