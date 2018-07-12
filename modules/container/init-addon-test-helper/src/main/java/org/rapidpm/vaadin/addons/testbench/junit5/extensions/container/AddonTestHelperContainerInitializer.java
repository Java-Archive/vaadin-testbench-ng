/**
 * Copyright © 2017 Sven Ruppert (sven.ruppert@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.rapidpm.vaadin.addons.testbench.junit5.extensions.container;

import static org.rapidpm.vaadin.addons.testbench.junit5.extensions.container.NetworkFunctions.SERVER_IP;
import static org.rapidpm.vaadin.addons.testbench.junit5.extensions.container.NetworkFunctions.SERVER_PORT;
import static org.rapidpm.vaadin.addons.testbench.junit5.extensions.container.NetworkFunctions.freePort;
import static org.rapidpm.vaadin.addons.testbench.junit5.extensions.container.NetworkFunctions.localeIP;
import java.lang.reflect.Method;
import java.net.URI;
import org.eclipse.jetty.server.Server;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.frp.functions.CheckedFunction;
import org.rapidpm.frp.model.Result;
import org.vaadin.addonhelpers.TServer;
import com.google.auto.service.AutoService;

@AutoService(ContainerInitializer.class)
public class AddonTestHelperContainerInitializer implements ContainerInitializer, HasLogger {

  private Result<Server> server = Result.failure("not initialized so far..");

  @Override
  public void beforeAll(Class<?> testClass, ExtensionContext context) throws Exception {
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
  public void beforeEach(Method testMethod, ExtensionContext context) throws Exception {
    // NOOP
  }

  @Override
  public void afterEach(Method testMethod, ExtensionContext context) throws Exception {
    // NOOP
  }

  @Override
  public void afterAll(Class<?> testClass, ExtensionContext context) throws Exception {
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
