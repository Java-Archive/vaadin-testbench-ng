package org.rapidpm.vaadin.addons.testbench.junit5.pageobject;

import static java.lang.System.getProperties;
import static org.rapidpm.vaadin.addons.webdriver.WebDriverFunctions.takeScreenShot;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.frp.functions.CheckedExecutor;
import org.rapidpm.vaadin.addons.webdriver.HasDriver;

public interface PageObject extends HasDriver, HasLogger {


  String DEFAULT_PROTOCOL = "http";
  String DEFAULT_IP       = "127.0.0.1";

  String DEFAULT_SERVLET_PORT   = "80";
  String DEFAULT_SERVLET_WEBAPP = "/";

  String SERVER_PROTOCOL = "server.protocol";
  String SERVER_IP       = "server.ip";
  String SERVER_PORT     = "server.port";
  String SERVER_WEBAPP   = "server.webapp";


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
    return () -> property().apply(SERVER_PROTOCOL, DEFAULT_PROTOCOL);
  }

  default Supplier<String> ip() {
    return () -> property().apply(SERVER_IP, DEFAULT_IP);
  }

  default Supplier<String> port() {
    //TODO per properties
    return () -> property().apply(SERVER_PORT, DEFAULT_SERVLET_PORT);
  }

  //TODO per properties
  default Supplier<String> webapp() {
    return () -> property().apply(SERVER_WEBAPP, DEFAULT_SERVLET_WEBAPP);
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