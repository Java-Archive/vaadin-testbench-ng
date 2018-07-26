package org.rapidpm.vaadin.addons.testbench.junit5.extensions.container;

import com.google.auto.service.AutoService;
import org.apache.meecrowave.Meecrowave;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.rapidpm.dependencies.core.logger.HasLogger;

import java.lang.reflect.Method;

import static org.rapidpm.vaadin.addons.junit5.extensions.ExtensionFunctions.store;
import static org.rapidpm.vaadin.addons.testbench.junit5.extensions.container.NetworkFunctions.localeIP;

@AutoService(ContainerInitializer.class)
public class MeecrowaveContainerInitializer implements ContainerInitializer, HasLogger {

  private Meecrowave meecrowave;

  @Override
  public void beforeAll(Class<?> testClass, ExtensionContext context) throws Exception {

  }

  @Override
  public void beforeEach(Method testMethod, ExtensionContext context) throws Exception {
    meecrowave = new Meecrowave(new Meecrowave.Builder() {
      {
        randomHttpPort();
        setHost(localeIP().get());
        setTomcatScanning(true);
        setTomcatAutoSetup(false);
        setHttp2(true);
      }
    }).bake();

    store().apply(context).put(NetworkFunctions.SERVER_IP, meecrowave.getConfiguration().getHost());
    store().apply(context).put(NetworkFunctions.SERVER_PORT, meecrowave.getConfiguration().getHttpPort());
    store().apply(context).put(NetworkFunctions.SERVER_WEBAPP, "/");

  }

  @Override
  public void afterEach(Method testMethod, ExtensionContext context) throws Exception {
    meecrowave.close();
  }

  @Override
  public void afterAll(Class<?> testClass, ExtensionContext context) throws Exception {

  }
}
