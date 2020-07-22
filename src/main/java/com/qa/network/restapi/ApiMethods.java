package com.qa.network.restapi;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import org.testng.Reporter;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import static com.qa.network.NetworkHelper.getClient;
import static com.qa.network.NetworkHelper.getGson;
import static com.qa.network.NetworkHelper.jsonType;
import static java.util.Objects.requireNonNull;

public class ApiMethods {

	public static final String CHARSET_UTF_8 = "application/json; charset=utf-8";

	private final static MediaType mediaType;

	private final static JsonParser parser;

	static {
		mediaType = MediaType.parse(jsonType);

		parser = new JsonParser();
	}

	public static String getresponse(String url, String userkey) throws IOException, NullPointerException {

		String responseString = triggerAPICall(url, userkey);

		parseAndPrintJsonString(responseString);

		return responseString;
	}

	public static String getresponsecode(String url, String userkey) throws IOException, NullPointerException {

		String responseString = triggerAPICall1(url, userkey);

		parseAndPrintJsonString(responseString);

		return responseString;
	}

	private static String triggerAPICall(String url, String userkey) throws IOException {
		Request request = buildrestRequest(url, userkey);

		Response response = getClient().newCall(request).execute();
		int responsecode = response.code();
		System.out.println("responsecode" + responsecode);

		return requireNonNull(response.body()).string();
	}

	private static String triggerAPICall1(String url, String userkey) throws IOException {
		Request request = buildrestRequest(url, userkey);

		int response = getClient().newCall(request).execute().code();

		return requireNonNull(response).toString();
	}

	public static String parseAndPrintJsonString(String responseString) {
		String result = responseString;
		try {
			JsonElement element = parser.parse(responseString);
			result = getGson().toJson(element);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		}
		Reporter.log("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n", true);
		Reporter.log("Response Body Json: " + result, true);
		Reporter.log("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=", true);
		return result;
	}

	private static Request buildrestRequest(String url, String userkey) {
		return new Builder().url(url).get().addHeader("Accept", CHARSET_UTF_8).addHeader("user-key", userkey).build();
	}

}
