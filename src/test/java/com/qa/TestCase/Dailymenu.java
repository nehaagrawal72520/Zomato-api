package com.qa.TestCase;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.jayway.restassured.path.json.JsonPath;
import com.qa.network.restapi.ApiMethods;

public class Dailymenu {

	static String URL = "https://developers.zomato.com/api/v2.1/dailymenu?res_id=16507624";
	static String URL1 = "https://developers.zomato.com/api/v2.1/dailymenu?res_id=165076";
	static String userkey = "82cf521776cb3d225ae238f11b7d69b1";
	static String actualLoginResponseStatus;
	static boolean statusmessage;
	static String statuscheck;

	public Dailymenu() {
		super();
	}

	// response code
	@Test(priority = 5)
	public static void checksuccessresponsecode() throws Throwable {

		actualLoginResponseStatus = ApiMethods.getresponsecode(URL, userkey);// code

		Assert.assertEquals(actualLoginResponseStatus, "200");
		Reporter.log("getting success response", true);

	}

	// with invalid entity-id
	@Test(priority = 6)
	public static void checkrestid() throws Throwable {

		String y = ApiMethods.getresponse(URL1, userkey);
		JsonPath jsonPathObj = new JsonPath(y);

		if (jsonPathObj != null && jsonPathObj.getString("code") != null) {
			actualLoginResponseStatus = jsonPathObj.getString("code");
			jsonPathObj.getString("message");
			System.out.println(actualLoginResponseStatus);
		}
		Assert.assertEquals(actualLoginResponseStatus, "400");
		Assert.assertEquals(jsonPathObj.getString("message"), "No Daily Menu Available");
		Reporter.log("No daily menu available with invalid entity-id", true);

	}

	@Test(priority = 7)
	public static void validatedailymenu() throws Throwable {

		String menu = ApiMethods.getresponse(URL, userkey);

		boolean responsebody = false;
		JsonPath jsonPathObj = new JsonPath(menu);

		if (jsonPathObj != null && jsonPathObj.getString("daily_menus") != null) {
			actualLoginResponseStatus = jsonPathObj.getString("daily_menus");
			 statuscheck = jsonPathObj.getString("status");
			responsebody = actualLoginResponseStatus != null;
			
		}

		Assert.assertEquals(responsebody, true);
		Assert.assertEquals(statuscheck, "success");
		Reporter.log("Response is showing correctly ", true);

	}
}
