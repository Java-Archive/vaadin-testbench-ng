package org.rapidpm.vaadin.addons.testbench.junit5.extensions.container;

import com.google.auto.service.AutoService;
import io.undertow.Undertow;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.dependencies.core.net.PortUtils;
import org.rapidpm.frp.model.Result;
import org.rapidpm.vaadin.nano.CoreUIService;

import java.lang.reflect.Method;

import static java.lang.System.getProperty;
import static java.lang.System.setProperty;
import static org.rapidpm.vaadin.addons.testbench.junit5.extensions.container.NetworkFunctions.localeIP;


@AutoService(ContainerInitializer.class)
public class NanoUndertowV08ContainerInitializer implements ContainerInitializer, HasLogger {

  private Result<CoreUIService> serviceResult = Result.failure("not initialized so far");

  @Override
  public void beforeAll(Class<?> testClass, ExtensionContext context) throws Exception {

  }

  @Override
  public void beforeEach(Method testMethod, ExtensionContext context) throws Exception {
    String          localIP   = localeIP().get();
    final PortUtils portUtils = new PortUtils();
    setProperty(CoreUIService.CORE_UI_SERVER_HOST, localIP);
    setProperty(CoreUIService.CORE_UI_SERVER_PORT, portUtils.nextFreePortForTest() + "");

    //mapping to TB Vars
    setProperty(NetworkFunctions.SERVER_WEBAPP, "/");
    setProperty(NetworkFunctions.SERVER_IP, getProperty(CoreUIService.CORE_UI_SERVER_HOST));
    setProperty(NetworkFunctions.SERVER_PORT, getProperty(CoreUIService.CORE_UI_SERVER_PORT));

    serviceResult = Result
        .success(new CoreUIService())
        .ifPresent(CoreUIService::startup);
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
