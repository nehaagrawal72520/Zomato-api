package com.qa.network;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


import static java.util.Objects.requireNonNull;
import static java.util.concurrent.TimeUnit.SECONDS;

import org.testng.Reporter;


public class NetworkHelper {
  
  public static final String jsonType = "application/json";
  
  public static OkHttpClient CLIENT;
  
  private static Gson GSON;
  
  
  public synchronized static Gson getGson() {
    if (GSON == null) {
      GSON = new GsonBuilder().setLenient().setPrettyPrinting().create();
    }
    return GSON;
  }
  
  
  public synchronized static OkHttpClient getClient() {
    if (CLIENT == null) {
      CLIENT = new OkHttpClient.Builder().connectTimeout(60, SECONDS).writeTimeout(60, SECONDS)
          .readTimeout(60, SECONDS)
          // .addNetworkInterceptor(getNetworkLoggingInterceptor())
          .build();
    }
    return CLIENT;
  }
  
  
  private static Interceptor getNetworkLoggingInterceptor() throws NullPointerException {
    return chain -> {
      Request requestOg = chain.request();
      if (shouldLogRequest()) {
        // TODO :: Add logging statement
        // logRequest(requestOg.newBuilder().build());
        Reporter.log(requestOg.newBuilder().build().toString() , true);
      }
      Response responseOg = chain.proceed(requestOg);
      String rawJson = responseOg.body().string();
      // TODO :: Add logging statement
      Reporter.log("Logging Interceptor. Raw JSON: " + rawJson,true);
      return rebuildNewResponse(responseOg, rawJson);
    };
  }
  
  
  private static boolean shouldLogRequest() {
    return true;
  }
  
  
  private static Response rebuildNewResponse(Response responseOg, String rawJson) throws NullPointerException {
    MediaType contentType = requireNonNull(responseOg.body()).contentType();
    return responseOg.newBuilder().body(ResponseBody.create(contentType, rawJson)).headers(responseOg.headers())
        .message(responseOg.message()).code(responseOg.code()).build();
  }
}
