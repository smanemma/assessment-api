package stepDefinition;

import constants.Constants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.junit.Assert;

import util.PropertyLoader;


public class getSteps {


    private static Integer id;
    private static Response response;
    private static String jsonString;
    RequestSpecification request;
    private static Logger logger = Logger.getLogger(getSteps.class);
    @Given("populate get url")
    public void populate_get_url() {
        RestAssured.baseURI = PropertyLoader.getConfigReader().getProperty(Constants.API_GET_URL);
    }
    @When("enter username and password")
    public void enter_username_and_password() {
       // enter user name password
    }
    @Then("succeesfully logged in")
    public void succeesfully_logged_in() {

    }
    @Given("perform Get operation for user")
    public void perform_Get_operation_for_user() {
        System.setProperty("http.protocols","TLSv1,TLSv1.1,TLSv1.2");
        request = RestAssured.given();
        request.header(Constants.HEADER_KEY1, Constants.HEADER_VALUE1);
    }

    @When("perform Get for the user {string}")
    public void perform_Get_for_the_user(String path) {
        response = request.get(path);
    }

    @Then("should see response code is {int}")
    public void should_see_response_code_is(int httpCode) {
        String jsonString = response.asString();
        logger.info("User Details :"+jsonString);
          id = JsonPath.from(jsonString).get("id");
        String  name = JsonPath.from(jsonString).get("name");
        Assert.assertEquals (response.getStatusCode(),httpCode);
    }

    @Given("perform Get operation for nonmatching user")
    public void perform_Get_operation_for_nonmatching_user() {
        System.setProperty("http.protocols","TLSv1,TLSv1.1,TLSv1.2");
        request = RestAssured.given();
        request.header(Constants.HEADER_KEY1, Constants.HEADER_VALUE1);
    }

    @When("perform get for the user with nonmatching userid {string}")
    public void perform_get_for_the_user_with_nonmatching_userid(String path) {
        response = request.get(path);
    }

    @Then("should verify response code {int}")
    public void should_verify_response_code(int httpCode) {
        Assert.assertEquals (response.getStatusCode(),httpCode);
    }


    @Given("perform Get operation for checking invalid user")
    public void perform_Get_operation_for_checking_invalid_user() {
        System.setProperty("http.protocols","TLSv1,TLSv1.1,TLSv1.2");
        request = RestAssured.given();
        request.header(Constants.HEADER_KEY1, Constants.HEADER_VALUE1);
    }

    @When("perform get for th user with unformat {string}")
    public void perform_get_for_th_user_with_unformat(String path) {

        response = request.get(path);
    }
    @Then("should verify response {int}")
    public void should_verify_response(int httpCode) {
        Assert.assertEquals (response.getStatusCode(),httpCode);
    }

}