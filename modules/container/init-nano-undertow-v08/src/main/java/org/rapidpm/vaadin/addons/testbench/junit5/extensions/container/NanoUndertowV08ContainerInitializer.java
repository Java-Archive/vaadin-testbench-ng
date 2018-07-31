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

import com.google.auto.service.AutoService;
import io.undertow.Undertow;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.dependencies.core.net.PortUtils;
import org.rapidpm.frp.model.Result;
import org.rapidpm.vaadin.nano.Config;
import org.rapidpm.vaadin.nano.CoreUIService;

import java.lang.reflect.Method;

import static org.rapidpm.vaadin.addons.junit5.extensions.ExtensionFunctions.store;
import static org.rapidpm.vaadin.addons.testbench.junit5.extensions.container.NetworkFunctions.localeIP;


@AutoService(ContainerInitializer.class)
public class NanoUndertowV08ContainerInitializer implements ContainerInitializer, HasLogger {

  private Result<CoreUIService> serviceResult = Result.failure("not initialized so far");

  @Override
  public void beforeAll(Class<?> testClass, ExtensionContext context) throws Exception {

  }

  @Override
  public void beforeEach(Method testMethod, ExtensionContext context) throws Exception {
    String          localIP      = localeIP().get();
    final PortUtils portUtils    = new PortUtils();
    int             nextFreePort = portUtils.nextFreePortForTest();

    store().apply(context).put(NetworkFunctions.SERVER_IP, localIP);
    store().apply(context).put(NetworkFunctions.SERVER_PORT, nextFreePort);
    store().apply(context).put(NetworkFunctions.SERVER_WEBAPP, "/");

    serviceResult = Result
        .success(new CoreUIService())
        .ifPresent(service -> service.startup(new Config(localIP, nextFreePort, null)));
  }

  @Override
  public void afterEach(Method testMethod, ExtensionContext context) throws Exception {
    serviceResult
        .flatMap(s -> s.undertow)
        .ifPresent(Undertow::stop);
  }

  @Override
  public void afterAll(Class<?> testClass, ExtensionContext context) throws Exception {

  }
}
