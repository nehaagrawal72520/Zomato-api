package com.qa.TestCase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.qa.network.restapi.ApiMethods;

public class Location {

	static String URL = "https://developers.zomato.com/api/v2.1/location_details?entity_id=36989&entity_type=group";
	static String URL1 = "https://developers.zomato.com/api/v2.1/location_details?entity_id=36&entity_type=group";
	static String URL2 = "https://developers.zomato.com/api/v2.1/location_details?entity_id=36&entity_type=bangalore";
	static String userkey = "82cf521776cb3d225ae238f11b7d69b1";
	static String actualLoginResponseStatus;

	public Location() {
		super();
	}

	// response code
	@Test(priority = 1)
	public static void checksuccessresponsecode() throws Throwable {

		actualLoginResponseStatus = ApiMethods.getresponsecode(URL, userkey);// code
		Assert.assertEquals(actualLoginResponseStatus, "200");
		Reporter.log("getting success response code", true);
	}

	// with invalid entity-id
	@Test(priority = 2)
	public static void checkentityid() throws Throwable {

		String y = ApiMethods.getresponse(URL1, userkey);
		JsonPath jsonPathObj = new JsonPath(y);

		if (jsonPathObj != null && jsonPathObj.getString("code") != null) {
			actualLoginResponseStatus = jsonPathObj.getString("code");
			jsonPathObj.getString("message");
			System.out.println(actualLoginResponseStatus);
		}
		Assert.assertEquals(actualLoginResponseStatus, "403");
		Assert.assertEquals(jsonPathObj.getString("message"), "Invalid location parameters");
		Reporter.log("getting failure response with invalid entity-id", true);

	}

	// with invalid entity-type
	@Test(priority = 3)
	public static void checkentitytype() throws Throwable {

		String y = ApiMethods.getresponse(URL2, userkey);
		JsonPath jsonPathObj = new JsonPath(y);

		if (jsonPathObj != null && jsonPathObj.getString("code") != null) {
			actualLoginResponseStatus = jsonPathObj.getString("code");
			jsonPathObj.getString("message");
			System.out.println(actualLoginResponseStatus);
		}
		Assert.assertEquals(actualLoginResponseStatus, "403");
		Assert.assertEquals(jsonPathObj.getString("message"), "Invalid location parameters");
		Reporter.log("getting failure response with invalid entity-type", true);
	}

	// with invalid entity-type
	@Test(priority = 4)
	public static void validateentityidandtype() throws Throwable {

		String y = ApiMethods.getresponse(URL, userkey);

		JSONParser parse = new JSONParser();
		JSONObject jobj = (JSONObject) parse.parse(y);
		JSONObject locationobject = (JSONObject) jobj.get("location");

		Assert.assertEquals(locationobject.get("entity_type"), "group");

		Assert.assertEquals(locationobject.get("entity_id").toString(), "36989");
		Reporter.log("getting valid response body", true);

	}

}
