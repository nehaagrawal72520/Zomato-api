//package com.qa.lib;
//
//
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.events.WebDriverEventListener;
//import org.testng.Reporter;
//
//import static com.qa.BaseClass.Logger.log;
//
//
//public class WebElementListener implements WebDriverEventListener {
//  
//  public void beforeChangeValueOf(WebElement element, WebDriver driver) {
//    System.out.println("Value of the:" + element.toString() + " before any changes made");
//  }
//  
//  
//  public void afterChangeValueOf(WebElement element, WebDriver driver) {
//    System.out.println("Element value changed to: " + element.toString());
//  }
//  
//  
//  @Override
//  public void beforeAlertAccept(WebDriver arg0) {
//	  Reporter.log("Accepting alert prompt" , true);
//  }
//  
//  
//  @Override
//  public void afterAlertAccept(WebDriver arg0) {
//	  Reporter.log("Accepted alert prompt" , true);
//  }
//  
//  
//  @Override
//  public void afterAlertDismiss(WebDriver arg0) {
//	  Reporter.log("Dismissed alert prompt",true);
//  }
//  
//  
//  @Override
//  public void beforeAlertDismiss(WebDriver arg0) {
//	  Reporter.log("Dismissing alert prompt", true);
//  }
//  
//  
//  public void beforeNavigateTo(String url, WebDriver driver) {
//    System.out.println("Before navigating to: '" + url + "'");
//  }
//  
//  
//  public void afterNavigateTo(String url, WebDriver driver) {
//    System.out.println("Navigated to:'" + url + "'");
//  }
//  
//  
//  public void beforeNavigateBack(WebDriver driver) {
//    System.out.println("Navigating back to previous page");
//  }
//  
//  
//  public void afterNavigateBack(WebDriver driver) {
//    System.out.println("Navigated back to previous page");
//  }
//  
//  
//  public void beforeNavigateForward(WebDriver driver) {
//    System.out.println("Navigating forward to next page");
//  }
//  
//  
//  public void afterNavigateForward(WebDriver driver) {
//    System.out.println("Navigated forward to next page");
//  }
//  
//  
//  @Override
//  public void beforeNavigateRefresh(WebDriver arg0) {
//    // TODO Auto-generated method stub
//    
//  }
//  
//  
//  @Override
//  public void afterNavigateRefresh(WebDriver arg0) {
//    // TODO Auto-generated method stub
//    
//  }
//  
//  
//  public void beforeFindBy(By by, WebElement element, WebDriver driver) {
//    System.out.println("Trying to find Element By : " + by.toString());
//  }
//  
//  
//  public void afterFindBy(By by, WebElement element, WebDriver driver) {
//    System.out.println("Found Element By : " + by.toString());
//  }
//  
//  
//  public void beforeClickOn(WebElement element, WebDriver driver) {
//    System.out.println("Trying to click on: " + element.toString());
//  }
//  
//  
//  public void afterClickOn(WebElement element, WebDriver driver) {
//    System.out.println("Clicked on: " + element.toString());
//  }
//  
//  
//  @Override
//  public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
//    // TODO Auto-generated method stub
//    
//  }
//  
//  
//  @Override
//  public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
//    // TODO Auto-generated method stub
//    
//  }
//  
//  
//  /*
//   * non overridden methods of WebListener class
//   */
//  public void beforeScript(String script, WebDriver driver) {
//  }
//  
//  
//  public void afterScript(String script, WebDriver driver) {
//  }
//  
//  
//  @Override
//  public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
//	  Reporter.log("Switching to window: " + arg0, true);
//  }
//  
//  
//  @Override
//  public void afterSwitchToWindow(String arg0, WebDriver arg1) {
//	  Reporter.log("Switched to window: " + arg0, true);
//  }
//  
//  
////  public void onException(Throwable error, WebDriver driver) {
////	  Reporter.log("<><> Exception occurred: <><>\n" + "Error Message: " + error.getMessage());
////	  Reporter.log("<><>");
////    try {
////      int idOr0 = SqsRepository.getEmployeeUanAttemptIdOr0();
////      String errorType = error.getClass().getSimpleName();
////      AL.getPhoto("Exception-" + idOr0 + "__Step-ElementInteractionFailed__Msg-" + errorType);
////    } catch (Exception e) {
////      e.printStackTrace();
////    }
////  }
//  
//  
////  @Override
////  public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
////    log("Taking screenshot: " + arg0);
////  }
//  
////  
////  @Override
////  public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
////    log("Took screenshot: " + arg0);
////  }
//}
