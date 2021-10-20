package RestApiTests;

import java.util.Date;

import RestApi.ApiUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
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

  static final String EXISTING_USER_MESSAGE = "User with the specified username already exists!";
  static final String FIRST_NAME_ERROR_MESSAGE = "First Name should be less than 15";


  public RequestSpecification setUpRequest(boolean hasRequestBody) {
    RestAssured.baseURI = ApiUtils.BASE_URL;
    RequestSpecification request = RestAssured.given();
    if(hasRequestBody) { request.header("Content-Type", "application/json");};
    return request;
  }

  public JSONObject createUserRequestJSONObject(String firstName, String lastName, String password, String username) {
    JSONObject userObject = new JSONObject();
    if (firstName != null) {
      userObject.put("firstName", firstName);
    }
    if (lastName != null) {
      userObject.put("lastName", lastName);
    }
    if (password != null) {
      userObject.put("password", password);
    }
    if (username != null) {
      userObject.put("username", username);
    }
    return userObject;
  }

  public Response doPutUsersRegister(String firstName, String lastName, String password, String username) {
    log.debug("doPutUsersRegister(" + firstName + ", " + lastName + ", "+ password + ", "+ username + ")");
    RequestSpecification request = setUpRequest(true);
    JSONObject userRequestBody = createUserRequestJSONObject(firstName, lastName, password , username);
    request.body(userRequestBody.toString());
    Response response = request.request(Method.PUT, ApiUtils.REGISTER_URL);
    return response;
  }

  public ResponseBody doGetUserUsername(String username) {
    log.debug("doGetUser/" + username);
    Response response = setUpRequest(false).request(Method.GET, ApiUtils.USERS_URL + "/" + username);
    ResponseBody responseBody = response.getBody();
    log.debug(responseBody.asString());
    return responseBody;
  }

  public boolean verifyResponseCode(Response response, int code)  {
    log.debug("verifyResponseCode()");
    int actualResponseCode = response.getStatusCode();
    log.debug("Response code: " + actualResponseCode);
    log.debug(response.getBody().asString());
    return actualResponseCode==code;
  }

  public void doDeleteUsersDeleteAllUsers() {
    log.debug("doDeleteUsersDeleteAllUsers()");
    Response response = setUpRequest(false).request(Method.DELETE, ApiUtils.DELETE_ALL_URL);
    assert verifyResponseCode(response, 200) : "Wrong response. Expected 200. Actual" + response.getStatusCode();
  }

  public void verifyResponseBody(JsonPath body) {
    assert body.get("id")!=null;

  }

}
