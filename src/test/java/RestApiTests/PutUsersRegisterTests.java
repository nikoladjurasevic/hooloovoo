package RestApiTests;
import RestApi.ApiUtils;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class PutUsersRegisterTests extends RestApiBaseTest {

  @Test
  public void testCreateUserWithAllRequiredParams() {
    String userName = "dragan" + currentTime;
    Response response = doPutUsersRegister("Dragan3", "Dragovanovic3", password , userName);
    assert verifyResponseCode(response, 201) : "Expected response code: 201. Actual: " + response.getStatusCode();
    ResponseBody body = response.getBody();
    log.debug(body.asString());
    ResponseBody getUserBody = doGetUserUsername(userName);
    JsonPath a = getUserBody.jsonPath();
    log.debug(a.get("username"));
  }


  @Test
  public void testCreateUserWithEmptyFirstName() {
    String userName = "dragan" + currentTime;
    Response response = doPutUsersRegister("", "Dragan", password , userName);
    assert verifyResponseCode(response, 400) : "Expected response code: 400. Actual: " + response.getStatusCode();
    String actualResponseBody = response.getBody().asString();
    log.debug(actualResponseBody);
    assert actualResponseBody.equals(FIRST_NAME_ERROR_MESSAGE) : "Expected response body to be :  " + FIRST_NAME_ERROR_MESSAGE + " Actual:" + actualResponseBody;
  }

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

  @Test
  public void testCreateUserWith16CharachterFirstName() {
    String userName = "blablablablablab";
    Response response = doPutUsersRegister(userName, "bla", password , userName);
    assert verifyResponseCode(response, 400) : "Expected response code: 400. Actual: " + response.getStatusCode();
    String actualResponseBody = response.getBody().asString();
    log.debug(actualResponseBody);
    assert actualResponseBody.equals(FIRST_NAME_ERROR_MESSAGE) : "Expected response body to be :  " + FIRST_NAME_ERROR_MESSAGE + " Actual:" + actualResponseBody;
  }

  @Test
  public void testTryToCreateUserWithEmail() {
    String userName = "jovan" + currentTime;
    RequestSpecification request = setUpRequest(true);
    JSONObject userRequestBody = createUserRequestJSONObject("Jovan", "Jovanovic", password , userName);
    userRequestBody.put("user_email", userName + "@.email.com");
    request.body(userRequestBody.toString());
    Response response = request.request(Method.PUT, ApiUtils.REGISTER_URL);
    assert verifyResponseCode(response, 201) : "Expected response code: 201. Actual: " + response.getStatusCode();
    ResponseBody body = response.getBody();
    assert body.jsonPath().get("user_email")==null : "Email should not be crated, but it is";
  }

  @Test
  public void testTryToCreateExistingUser() {
    String userName = "ivkica" + currentTime;
    Response response = doPutUsersRegister("Ivana", "Ivanovic", password , userName);
    assert verifyResponseCode(response, 201): "Expected response code: 201. Actual: " + response.getStatusCode();
    response = doPutUsersRegister("Ivana", "Ivanovic", password , userName);
    assert verifyResponseCode(response, 400): "Expected response code: 400. Actual: " + response.getStatusCode();
    String actualResponseBody = response.getBody().asString();
    assert actualResponseBody.equals(EXISTING_USER_MESSAGE) : "Expected response body to be :  " + EXISTING_USER_MESSAGE + " Actual:" + actualResponseBody;
  }



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
            {"bla" +currentTime , null, null, null},
            {null, "bla" +currentTime, null, null},
            {null, null, "bla" +currentTime, null},
            {null, null, null, "bla" +currentTime},

            {"bla" +currentTime, "bla" +currentTime, null, null},
            {"bla" +currentTime , null, "bla" +currentTime, null},
            {"bla" +currentTime , null, null, "bla" +currentTime},
            {"bla" +currentTime , "bla" +currentTime, "bla" +currentTime, null},
            {"bla" +currentTime , "bla" +currentTime, null, "bla" +currentTime},

            //for some reason this test beneath will first check firstname and then fail
            {"a" +currentTime , null, "a" +currentTime, "a" +currentTime},


            {null , "bla" +currentTime, null, null},
            {null , "bla" +currentTime, "bla" +currentTime, null},
            {null , "bla" +currentTime, null, "bla" +currentTime},
            {null , "bla" +currentTime, "bla" +currentTime, "bla" +currentTime},

            {null , null, "something" +currentTime, null},
            {null , null, "something" +currentTime, "something" +currentTime},

            {null , null, null, "something" +currentTime},
    };
  }

}
