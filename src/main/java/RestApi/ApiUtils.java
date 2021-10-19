package RestApi;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class ApiUtils {
  protected static final Logger log = LogManager.getLogger(ApiUtils.class);

  protected static final String BASE_URL = "http://localhost:8080";
  protected static final String USERS_URL = "/users";
  protected static final String DELETE_ALL_URL = USERS_URL + "/deleteAllUsers";
  protected static final String LOGIN_URL = USERS_URL + "/login";
  protected static final String REGISTER_URL = USERS_URL + "/register";

}
