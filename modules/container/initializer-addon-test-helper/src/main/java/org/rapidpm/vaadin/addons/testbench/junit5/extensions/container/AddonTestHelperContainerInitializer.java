package org.rapidpm.vaadin.addons.testbench.junit5.extensions.container;

import com.google.auto.service.AutoService;
import org.eclipse.jetty.server.Server;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.frp.functions.CheckedFunction;
import org.rapidpm.frp.model.Result;
import org.vaadin.addonhelpers.TServer;

import java.lang.reflect.Method;
import java.net.URI;

import static org.rapidpm.vaadin.addons.testbench.junit5.extensions.container.NetworkFunctions.*;

@AutoService(ContainerInitializer.class)
public class AddonTestHelperContainerInitializer implements ContainerInitializer, HasLogger {

  private Result<Server> server = Result.failure("not initialized so far..");

  @Override
  public void beforeAll(Class<?> testClass) throws Exception {
    final String userVaadinServerIP = localeIP().get();
    logger().info(
        SERVER_IP + " ServletContainerExtension - will be -> " + userVaadinServerIP);

    freePort()
        .get()
        .ifAbsent(() -> {
          throw new RuntimeException("no free Port available..");
        })
        .ifPresent((port) -> {
          System.setProperty(SERVER_IP, userVaadinServerIP);
          System.setProperty(SERVER_PORT, String.valueOf(port));
          ((CheckedFunction<Integer, Server>) p -> new TServer().startServer(p))
              .apply(port)
              .ifPresentOrElse(ok -> {
                                 server = Result.success(ok);
                                 logger().info("Started server on port: " + port);
                               },
                               failed -> {
                                 String message = "failed to start TServer for port " + port + " -> " + failed;
                                 logger().warning(message);
                                 throw new RuntimeException(message);
                               }
              );
        });
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
    server
        .flatMap((CheckedFunction<Server, String>) server -> {
          URI serverURI = server.getURI();
          server.stop();
          return serverURI.toASCIIString();
        })
        .ifPresentOrElse(serverURI -> logger().info("Stopped server on : " + serverURI),
                         failed -> logger().warning("no active server available to stop " + failed)
        );
  }
}
