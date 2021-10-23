package RestApiTests;
import RestApi.ApiUtils;
import SeleniumTests.DateTimeUtils;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class PutUsersRegisterTests extends RestApiBaseTest {

  /**
   * Do a register user request with all 4 valid credentials (firstname, lastname, password, username)
   * Expected result: user is crated (201 Created)
   * Do a get/User/{username} and verify that user is created
   */
  @Test
  public void testCreateUserWithAllRequiredParams() {
    String userName = "dragan" + DateTimeUtils.getCurrentTimeAsUniqueId();
    Response response = doPutUsersRegister("Dragan", "Dragovanovic", password , userName);
    assert verifyResponseCode(response, 201) : "Expected response code: 201. Actual: " + response.getStatusCode();
    ResponseBody body = response.getBody();
    log.debug(body.asString());
    ResponseBody getUserBody = doGetUserUsername(userName);
    JsonPath a = getUserBody.jsonPath();
    log.debug(a.get("username"));
  }


  /**
   * Try to create user with empty string for a fist name
   * Expected result: user is not created (400 Bad request)
   */
  @Test
  public void testCreateUserWithEmptyFirstName() {
    String userName = "dragan" + DateTimeUtils.getCurrentTimeAsUniqueId();
    Response response = doPutUsersRegister("", "Dragan", password , userName);
    assert verifyResponseCode(response, 400) : "Expected response code: 400. Actual: " + response.getStatusCode();
    String actualResponseBody = response.getBody().asString();
    log.debug(actualResponseBody);
    assert actualResponseBody.equals(FIRST_NAME_ERROR_MESSAGE) : "Expected response body to be :  " + FIRST_NAME_ERROR_MESSAGE + " Actual:" + actualResponseBody;
  }

  /**
   * Try to create user with 15character string for a fist name
   * Expected result: user is created (201 Created)
   */
  @Test
  public void testCreateUserWith15CharachterFirstName() {
    String userName = "blablablablabla";
    try {
      Response response = doPutUsersRegister(userName, "bla", password , userName);
      assert verifyResponseCode(response, 201) : "Expected response code: 201. Actual: " + response.getStatusCode();
      String actualResponseBody = response.getBody().asString();
      assert actualResponseBody.contains("createdAt") : "User is not crated";
      log.debug(actualResponseBody);
    }finally {
      doDeleteUsersDeleteAllUsers();
    }
  }

  /**
   * Try to create user with 16character string for a fist name
   * Expected result: user is not created (400 Bad request)
   */
  @Test
  public void testCreateUserWith16CharachterFirstName() {
    String userName = "blablablablablab";
    Response response = doPutUsersRegister(userName, "bla", password , userName);
    assert verifyResponseCode(response, 400) : "Expected response code: 400. Actual: " + response.getStatusCode();
    String actualResponseBody = response.getBody().asString();
    log.debug(actualResponseBody);
    assert actualResponseBody.equals(FIRST_NAME_ERROR_MESSAGE) : "Expected response body to be :  " + FIRST_NAME_ERROR_MESSAGE + " Actual:" + actualResponseBody;
  }

  /**
   * Try to create user with email key and value in request (non existing key in body template)
   * Expected result: user is created (201 Created)
   * User is created but email value is still null when doing a get/Users/{username}
   */
  @Test
  public void testTryToCreateUserWithEmail() {
    String userName = "jovan" + DateTimeUtils.getCurrentTimeAsUniqueId();
    RequestSpecification request = setUpRequest(true);
    JSONObject userRequestBody = createUserRequestJSONObject("Jovan", "Jovanovic", password , userName);
    userRequestBody.put("user_email", userName + "@.email.com");
    request.body(userRequestBody.toString());
    Response response = request.request(Method.PUT, ApiUtils.REGISTER_URL);
    assert verifyResponseCode(response, 201) : "Expected response code: 201. Actual: " + response.getStatusCode();
    ResponseBody body = response.getBody();
    assert body.jsonPath().get("user_email")==null : "Email should not be crated, but it is";
  }

  /**
   * Try to create user which is already registered
   * Expected result: user is not created (400 Created)
   */
  @Test
  public void testTryToCreateExistingUser() {
    String userName = "ivkica" + DateTimeUtils.getCurrentTimeAsUniqueId();
    Response response = doPutUsersRegister("Ivana", "Ivanovic", password , userName);
    assert verifyResponseCode(response, 201): "Expected response code: 201. Actual: " + response.getStatusCode();
    response = doPutUsersRegister("Ivana", "Ivanovic", password , userName);
    assert verifyResponseCode(response, 400): "Expected response code: 400. Actual: " + response.getStatusCode();
    String actualResponseBody = response.getBody().asString();
    assert actualResponseBody.equals(EXISTING_USER_MESSAGE) : "Expected response body to be :  " + EXISTING_USER_MESSAGE + " Actual:" + actualResponseBody;
  }


  /**
   * Try to create user with invalid request (1 or more required parameters is mising in request body)
   * Expected result: user is not created (500 Server error)
   */
  @Test(dataProvider = "invalidTestData")
  public void testInvalidForm(String firstName, String lastName, String password, String username ) {
    log.debug("testInvalidForm(" + firstName + ", " + lastName + ", " + password + ", " + username + ")");
    Response response = doPutUsersRegister(firstName, lastName, password , username);
    assert verifyResponseCode(response, 500);
  }

  @DataProvider(name = "invalidTestData")
  public Object[][] invalidTestData() {
    return new Object[][] {
            {null , null, null, null},
            {"bla" +DateTimeUtils.getCurrentTimeAsUniqueId() , null, null, null},
            {null, "bla" +DateTimeUtils.getCurrentTimeAsUniqueId(), null, null},
            {null, null, "bla" +DateTimeUtils.getCurrentTimeAsUniqueId(), null},
            {null, null, null, "bla" +DateTimeUtils.getCurrentTimeAsUniqueId()},

            {"bla" +DateTimeUtils.getCurrentTimeAsUniqueId(), "bla" +DateTimeUtils.getCurrentTimeAsUniqueId(), null, null},
            {"bla" +DateTimeUtils.getCurrentTimeAsUniqueId() , null, "bla" +DateTimeUtils.getCurrentTimeAsUniqueId(), null},
            {"bla" +DateTimeUtils.getCurrentTimeAsUniqueId() , null, null, "bla" +DateTimeUtils.getCurrentTimeAsUniqueId()},
            {"bla" +DateTimeUtils.getCurrentTimeAsUniqueId() , "bla" +DateTimeUtils.getCurrentTimeAsUniqueId(), "bla" +DateTimeUtils.getCurrentTimeAsUniqueId(), null},
            {"bla" +DateTimeUtils.getCurrentTimeAsUniqueId() , "bla" +DateTimeUtils.getCurrentTimeAsUniqueId(), null, "bla" +DateTimeUtils.getCurrentTimeAsUniqueId()},

            //for some reason this test beneath will first check firstname and then fail
            {"a" +DateTimeUtils.getCurrentTimeAsUniqueId() , null, "a" +DateTimeUtils.getCurrentTimeAsUniqueId(), "a" +DateTimeUtils.getCurrentTimeAsUniqueId()},


            {null , "bla" +DateTimeUtils.getCurrentTimeAsUniqueId(), null, null},
            {null , "bla" +DateTimeUtils.getCurrentTimeAsUniqueId(), "bla" +DateTimeUtils.getCurrentTimeAsUniqueId(), null},
            {null , "bla" +DateTimeUtils.getCurrentTimeAsUniqueId(), null, "bla" +DateTimeUtils.getCurrentTimeAsUniqueId()},
            {null , "bla" +DateTimeUtils.getCurrentTimeAsUniqueId(), "bla" +DateTimeUtils.getCurrentTimeAsUniqueId(), "bla" +DateTimeUtils.getCurrentTimeAsUniqueId()},

            {null , null, "bla" +DateTimeUtils.getCurrentTimeAsUniqueId(), null},
            {null , null, "bla" +DateTimeUtils.getCurrentTimeAsUniqueId(), "bla" +DateTimeUtils.getCurrentTimeAsUniqueId()},

            {null , null, null, "bla" +DateTimeUtils.getCurrentTimeAsUniqueId()},
    };
  }

}
