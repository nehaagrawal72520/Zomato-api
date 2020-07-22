package com.qa.TestCase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.jayway.restassured.path.json.JsonPath;
import com.qa.network.restapi.ApiMethods;

public class City {

	static String URL = "https://developers.zomato.com/api/v2.1/cities?q=Bangalore";
	static String URL1 = "https://developers.zomato.com/api/v2.1/cities?lat=12.9716&lon=77.5946";
	static String URL2 = "https://developers.zomato.com/api/v2.1/cities?city_ids=4%2C5%2C7";
	static String URL3 = "https://developers.zomato.com/api/v2.1/cities?city_ids=4%2C5%2C7&count=2";
	static String URL4 = "https://developers.zomato.com/api/v2.1/cities?city_ids=%40!";
	static String userkey = "82cf521776cb3d225ae238f11b7d69b1";
	static String actualLoginResponseStatus;

	public City() {
		super();
	}

	// response code
	@Test(priority = 1)
	public static void checksuccessresponsecode() throws Throwable {

		// combination of user-key and city name
		actualLoginResponseStatus = ApiMethods.getresponsecode(URL, userkey);// code
		Assert.assertEquals(actualLoginResponseStatus, "200");
		Reporter.log("Response is correct with userkey and city name", true);

		// combination of user-key ,latitude and longitude
		actualLoginResponseStatus = ApiMethods.getresponsecode(URL2, userkey);// code
		Assert.assertEquals(actualLoginResponseStatus, "200");
		Reporter.log("Response is correct with user key,latitude and longitude", true);

		// combination of user-key and city-id
		actualLoginResponseStatus = ApiMethods.getresponsecode(URL3, userkey);// code
		Assert.assertEquals(actualLoginResponseStatus, "200");
		Reporter.log("Response is correct with user keyand cityid", true);
	}

	// count no of response
	@Test(priority = 2)
	public static void checkcountresponse() throws Throwable {
		String y = ApiMethods.getresponse(URL3, userkey);
		JSONParser parse = new JSONParser();
		JSONObject jobj = (JSONObject) parse.parse(y);
		JSONArray jsonarr_1 = (JSONArray) jobj.get("location_suggestions");
		int arraysize = jsonarr_1.size();
		System.out.println(jsonarr_1.size());
		try {

			Assert.assertEquals(arraysize, 2);
			Reporter.log("Result count is equal is to given input", true);
			
		} catch (AssertionError e) {
			System.out.println("Result count of response doesn't match");
		}
	}

	// invalid api key and message check
	@Test(priority = 3)
	public static void check1() throws Throwable {

		String y = ApiMethods.getresponse(URL, "345");
		JsonPath jsonPathObj = new JsonPath(y);

		if (jsonPathObj != null && jsonPathObj.getString("code") != null) {
			actualLoginResponseStatus = jsonPathObj.getString("code");
			jsonPathObj.getString("message");
			System.out.println(actualLoginResponseStatus);
		}
		Assert.assertEquals(actualLoginResponseStatus, "403");
		Assert.assertEquals(jsonPathObj.getString("message"), "Invalid API Key");
		Reporter.log("Invalid api key", true);
	}

	//invalid city-id
	@Test(priority = 4)
	public static void checkcityid() throws Throwable {
		actualLoginResponseStatus = ApiMethods.getresponsecode(URL4, userkey);// code
		try {

			Assert.assertEquals(actualLoginResponseStatus, "400");
			Reporter.log("city id is not matching", true);
		} catch (AssertionError e) {
			System.out.println("invalid city id ");
		}
		
		
		
	}

}