package util;


import constants.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;


public class RestAssuredUtils {
    private static Logger logger = Logger.getLogger(RestAssuredUtils.class);

    /**
     * Set the URL
     * @param url
     */
    public static void setBaseURI(String url)
    {
        RestAssured.baseURI = url;
        logger.info("Set the URL"+url);
    }

    /**
     * Create the record in servie
     * @param id
     * @param title
     * @param body
     * @param userid
     * @return
     */
    public static Response createRecord(int id, String title, String body, int userid)
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.ID, id);
        jsonObject.put(Constants.TITLE, title);
        jsonObject.put(Constants.BODY, body);
        jsonObject.put(Constants.USERID, userid);
        logger.info("Created Jsong String Body"+jsonObject.toString());
        Response response =given().contentType(ContentType.JSON).body(jsonObject.toString()).when().post();
        if(null!=response)
        {
            logger.info("Valid Response::"+response.toString());
        }


        return response;
    }
    /**
     * Try to Create the record in servie with invalid data

     * @return
     */
    public static Response createRecordWithInvalidData(String invalidJson)
    {

        logger.info("Created Jsong String Body"+invalidJson);
        Response response =given().contentType(ContentType.JSON).body(invalidJson).when().post();
        if(null!=response)
        {
            logger.info("Invalid Response::"+response.asString());
        }


        return response;
    }
    /**
     * Reset base URL
     */
    public static void resetBaseURI() {
        RestAssured.baseURI = null;
    }

/*
    //Returns response
    public static Response getResponse() {
        //System.out.print("path: " + path +"\n");
        return get(path);
    }*/


}