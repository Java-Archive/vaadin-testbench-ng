package org.rapidpm.vaadin.addons.testbench.junit5.pageobject;

import static java.lang.System.getProperties;
import static org.rapidpm.vaadin.addons.webdriver.WebDriverFunctions.takeScreenShot;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.frp.functions.CheckedExecutor;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.container.NetworkFunctions;
import org.rapidpm.vaadin.addons.webdriver.HasDriver;

public interface PageObject extends HasDriver, HasLogger {


  default void loadPage() {
    final String url = url().get();
    logger().info("Navigate browser to " + url);
    getDriver().get(url);
  }

  default String getTitle() {
    return getDriver().getTitle();
  }

  default BiFunction<String, String, String> property() {
    return (key, defaultValue) -> (String) getProperties().getOrDefault(key, defaultValue);
  }

  default Supplier<String> protocol() {
    return () -> property().apply(NetworkFunctions.SERVER_PROTOCOL, NetworkFunctions.DEFAULT_PROTOCOL);
  }

  default Supplier<String> ip() {
    return () -> property().apply(NetworkFunctions.SERVER_IP, NetworkFunctions.DEFAULT_IP);
  }

  default Supplier<String> port() {
    //TODO per properties
    return () -> property().apply(NetworkFunctions.SERVER_PORT, NetworkFunctions.DEFAULT_SERVLET_PORT);
  }

  //TODO per properties
  default Supplier<String> webapp() {
    return () -> property().apply(NetworkFunctions.SERVER_WEBAPP, NetworkFunctions.DEFAULT_SERVLET_WEBAPP);
  }


  default Supplier<String> baseURL() {
    return () -> protocol().get() + "://" + ip().get() + ":" + port().get();
  }

  default Supplier<String> url() {
    return () -> baseURL().get() + webapp().get() + "/";
  }

  default void destroy() {
    ((CheckedExecutor) getDriver()::quit)
        .apply(null)
        .ifPresentOrElse(
            ok -> logger().info("webdriver quit -> OK"),
            failed -> logger().warning("webdriver quit failed -> " + failed)
        );

    ((CheckedExecutor) getDriver()::close)
        .apply(null)
        .ifPresentOrElse(
            ok -> logger().info("webdriver close -> OK"),
            failed -> logger().warning("webdriver close failed -> " + failed)
        );
  }

  default void screenshot() {
    takeScreenShot().accept(getDriver());
  }

}