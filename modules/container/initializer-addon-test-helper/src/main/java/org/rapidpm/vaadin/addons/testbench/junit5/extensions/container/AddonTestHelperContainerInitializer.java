package org.rapidpm.vaadin.addons.testbench.junit5.extensions.container;

import static org.rapidpm.vaadin.addons.testbench.junit5.extensions.container.NetworkFunctions.localeIP;
import java.lang.reflect.Method;
import org.eclipse.jetty.server.Server;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.vaadin.addonhelpers.TServer;
import com.google.auto.service.AutoService;

@AutoService(ContainerInitializer.class)
public class AddonTestHelperContainerInitializer implements ContainerInitializer, HasLogger {

  private int port;
  // TODO copy and pasted move to better place
  public static final String KEY_VAADIN_SERVER_IP = "server.ip";
  public static final String KEY_VAADIN_SERVER_PORT = "server.port";

  private Server server;

  @Override
  public void beforeAll(Class<?> testClass) throws Exception {
    final String userVaadinServerIP = localeIP().get();
    logger().info(
        KEY_VAADIN_SERVER_IP + " ServletContainerExtension - will be -> " + userVaadinServerIP);

    port = NetworkFunctions.freePort().getWithException();
    System.setProperty(KEY_VAADIN_SERVER_IP, userVaadinServerIP);

    System.setProperty(KEY_VAADIN_SERVER_PORT, String.valueOf(port));
    TServer tserver = new TServer();
    server = tserver.startServer(port);
    logger().info("Started server on port: " + port);

  }

  @Override
  public void beforeEach(Method testMethod) throws Exception {
    // NOOP
  }

  @Override
  public void afterEach(Method testMethod) throws Exception {
    // NOOP
  }

  @Override
  public void afterAll(Class<?> testClass) throws Exception {
    server.stop();
    logger().info("Stoped server on port: " + port);
  }
}
