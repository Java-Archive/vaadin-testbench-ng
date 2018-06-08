package org.rapidpm.vaadin.addons.testbench.junit5.extension.compattest;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.rapidpm.dependencies.core.logger.HasLogger;

import static org.rapidpm.vaadin.addons.webdriver.WebDriverFunctions.webdriverName;
import static org.rapidpm.vaadin.addons.webdriver.junit5.WebdriverExtensionFunctions.removeWebDriver;
import static org.rapidpm.vaadin.addons.webdriver.junit5.WebdriverExtensionFunctions.webdriver;

/**
 *
 */
public class PageObjectWebDriverCleanerExtension implements AfterEachCallback, HasLogger {

//  public static final String SESSION_ID = "SESSION_ID";

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    logger().info("PageObjectWebDriverCleanerExtension -> remove Webdriver");
    final WebDriver webDriver = webdriver().apply(context);
    logger().info("close webdriver of type " + webdriverName().apply(webDriver));
//    match(
//        matchCase(() -> failure("could not map driver to impl class " + TestbenchFunctions.webdrivername().apply(webDriver))),
//        matchCase(() -> webDriver instanceof RemoteWebDriver, () -> success(((RemoteWebDriver) webDriver).getSessionId().toString())),
//        matchCase(() -> webDriver instanceof TestBenchDriverProxy,
//                  () -> success(
//                      ((RemoteWebDriver)
//                          ((TestBenchDriverProxy) webDriver).getActualDriver())
//                          .getSessionId().toString())
//        )
//    ).ifPresentOrElse(
//        success -> store().apply(context).put(SESSION_ID, success),
//        message -> {
//          logger().warning(message);
//          store().apply(context).remove(SESSION_ID);
//        }
//    );
    webDriver.quit();
    removeWebDriver().accept(context);
  }
}
