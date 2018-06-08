package org.rapidpm.vaadin.addons.testbench.junit5.extensions.unittest;

import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.TestBenchDriverProxy;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.frp.model.Result;

import static org.rapidpm.vaadin.addons.webdriver.junit5.WebdriverExtensionFunctions.*;

public class ConvertWebdriverTestExtension implements BeforeEachCallback, AfterEachCallback, HasLogger {

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    logger().info("beforeEach  -> convert WebDriver to VaadinWebDriver");

    Result
        .ofNullable(webdriver().apply(context))
        .ifPresentOrElse(
            webDriver -> {
              removeWebDriver().accept(context);
              storeWebDriver().accept(context, TestBench.createDriver(webDriver));
            },
            failed -> logger().warning(failed)
        );
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    logger().info("afterEach  -> convert VaadinWebDriver to WebDriver");

    Result
        .ofNullable(webdriver().apply(context))
        .ifPresentOrElse(
            webDriver -> {
              //TODO not a clean life cycle -> compat tests
              if (webDriver instanceof TestBenchDriverProxy) {
                removeWebDriver().accept(context);
                storeWebDriver().accept(context, ((TestBenchDriverProxy) webDriver).getActualDriver());
              }
            },
            failed -> logger().warning(failed)
        );
  }

}
