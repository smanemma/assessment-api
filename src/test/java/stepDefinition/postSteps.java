package stepDefinition;


import constants.Constants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;
import util.PropertyLoader;


public class postSteps {
    private  Response response;
    private JSONObject requestParams;
    private RequestSpecification request;
    private String invalidJsonString;
    private static Logger logger = Logger.getLogger(postSteps.class);

    @Given("populate post url")
    public void populate_post_url() {
        RestAssured.baseURI = PropertyLoader.getConfigReader().getProperty(Constants.API_POST_URL);
    }
    @When("submit username and password")
    public void enter_username_and_password() {
        // enter user name password
    }
    @Then("logged in")
    public void succeesfully_logged_in() {

    }
    @Given("User Details as an Input {int} {string} {string} {int}")
    public void user_Details_as_an_Input(int id, String title, String body, Integer userid) {
        requestParams = new JSONObject();
        requestParams.put(Constants.ID, id);
        requestParams.put(Constants.TITLE, title);
        requestParams.put(Constants.BODY, body);
        requestParams.put(Constants.USERID, userid);
    }


    @Given("Wrong userDetails as an Input")
    public void wrong_userDetails_as_an_Input() {
        invalidJsonString=Constants.INVALID_JSON;
        System.setProperty("http.protocols","TLSv1,TLSv1.1,TLSv1.2");
    }

    @When("triggered post API call with wrong details")
    public void triggered_post_API_call_with_wrong_details() {
        request = RestAssured.given();
        request.header(Constants.HEADER_KEY1, Constants.HEADER_VALUE1);
        request.body(invalidJsonString);
        response = request.post();


    }
    @When("triggered Post API call with correct details")
    public void triggered_Post_API_call_with_correct_details() {
        request = RestAssured.given();
        request.header(Constants.HEADER_KEY1, Constants.HEADER_VALUE1);
        request.body(requestParams.toString());
        response = request.post();
    }

    @Then("validate the success response code {int}")
    public void validate_the_success_response_code(int httpCode) {
        String jsonString = response.asString();
        logger.info("Return json body "+jsonString);
        Assert.assertEquals (response.getStatusCode(),httpCode);
    }

    @Then("validate the error response code {int}")
    public void validate_the_error_response_code(int httpCode) {
        String jsonString = response.asString();
        logger.info("Return error json body "+jsonString);
        Assert.assertEquals (response.getStatusCode(),httpCode);
    }

    @Given("Pass user details {int} {string} {string} {int}")
    public void pass_user_details(int id, String title, String body, Integer userid) {
        requestParams = new JSONObject();
        requestParams.put(Constants.ID, id);
        requestParams.put(Constants.TITLE, title);
        requestParams.put(Constants.BODY, body);
        requestParams.put(Constants.USERID, userid);
    }

    @When("triggered post API call with user details")
    public void triggered_post_API_call_with_user_details() {
        request = RestAssured.given();
        request.header(Constants.HEADER_KEY1, Constants.HEADER_VALUE1);
        request.body(requestParams.toString());
        response = request.post();
    }
    @Then("validate the user id passed in body {int}")
    public void validate_the_user_id_passed_in_body(int invalidUserId) {
        int userId = response.jsonPath().getInt(Constants.USERID);
        Assert.assertNotEquals(userId,invalidUserId);
    }
}
