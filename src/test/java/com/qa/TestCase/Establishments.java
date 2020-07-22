package com.qa.TestCase;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.qa.network.restapi.ApiMethods;

public class Establishments {
	
	
	static String URL = "https://developers.zomato.com/api/v2.1/establishments?city_id=4";
	static String URL1 = "https://developers.zomato.com/api/v2.1/establishments?city_id=4%2C5%2C6";
	static String URL2 = "https://developers.zomato.com/api/v2.1/establishments?lat=12.9716&lon=77.5946";
	static String userkey = "82cf521776cb3d225ae238f11b7d69b1";
	static String actualLoginResponseStatus;

	public Establishments() {
		super();
	}

	// response code
	@Test(priority = 1)
	public static void checksuccessresponsecode() throws Throwable {

		actualLoginResponseStatus = ApiMethods.getresponsecode(URL, userkey);// code

		Assert.assertEquals(actualLoginResponseStatus, "200");
		Reporter.log("getting success response code", true);

	}

	// with invalid city-id
	@Test(priority = 2)
	public static void checkrestid() throws Throwable {

		String y = ApiMethods.getresponse(URL1, userkey);
		JsonPath jsonPathObj = new JsonPath(y);

		if (jsonPathObj != null && jsonPathObj.getString("code") != null) {
			actualLoginResponseStatus = jsonPathObj.getString("code");
			jsonPathObj.getString("message");
			System.out.println(actualLoginResponseStatus);
		}
		Assert.assertEquals(actualLoginResponseStatus, "400");
		Assert.assertEquals(jsonPathObj.getString("message"), "Invalid or missing parameter \"city_id\"");
		Reporter.log("City-id is not correct", true);

	}
	
	
	@Test(priority = 3)
	public static void checksuccessresponsecodewithlatitudelongitude() throws Throwable {

		actualLoginResponseStatus = ApiMethods.getresponsecode(URL2, userkey);// code
		Assert.assertEquals(actualLoginResponseStatus, "200");
		Reporter.log("getting success response with valid latitude and longitude", true);

	}
	
	
	//response body
	@Test(priority = 4)
	public static void checkestablishmentidresponse() throws Throwable {

		String y = ApiMethods.getresponse(URL2, userkey);
		JsonPath jsonPathObj = new JsonPath(y);

		if (jsonPathObj != null && jsonPathObj.getString("establishments") != null) {
			actualLoginResponseStatus = jsonPathObj.getString("establishments");
			
		}
		Assert.assertEquals(actualLoginResponseStatus!=null, true);
		Reporter.log("getting success response body", true);
		

	}
	

}
