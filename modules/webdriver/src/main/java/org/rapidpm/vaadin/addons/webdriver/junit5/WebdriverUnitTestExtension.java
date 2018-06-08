package org.rapidpm.vaadin.addons.webdriver.junit5;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.rapidpm.dependencies.core.logger.HasLogger;

import static org.rapidpm.vaadin.addons.webdriver.BrowserDriverFunctions.unittestingWebDriverInstance;
import static org.rapidpm.vaadin.addons.webdriver.junit5.WebdriverExtensionFunctions.removeWebDriver;
import static org.rapidpm.vaadin.addons.webdriver.junit5.WebdriverExtensionFunctions.storeWebDriver;
import static org.rapidpm.vaadin.addons.webdriver.junit5.WebdriverExtensionFunctions.webdriver;

/**
 *
 */
public class WebdriverUnitTestExtension implements BeforeEachCallback, AfterEachCallback, HasLogger {

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    logger().info("beforeEach  -> load default browser webdriver for unit testing");
    unittestingWebDriverInstance()
        .ifPresentOrElse(
            webDriver -> storeWebDriver().accept(context, webDriver),
            failed -> logger().warning(failed)
        );
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    logger().info("afterEach  -> will close and remove the webdriver now..");
    webdriver()
        .apply(context)
        .close();
    removeWebDriver().accept(context);
  }
}
