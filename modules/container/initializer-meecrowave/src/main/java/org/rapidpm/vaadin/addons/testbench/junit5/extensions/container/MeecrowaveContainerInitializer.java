package org.rapidpm.vaadin.addons.testbench.junit5.extensions.container;

import com.google.auto.service.AutoService;
import org.apache.meecrowave.Meecrowave;
import org.rapidpm.dependencies.core.logger.HasLogger;

import java.lang.reflect.Method;

import static java.lang.System.setProperty;
import static org.rapidpm.vaadin.addons.testbench.junit5.extensions.container.NetworkFunctions.localeIP;

@AutoService(ContainerInitializer.class)
public class MeecrowaveContainerInitializer implements ContainerInitializer, HasLogger {

  private Meecrowave meecrowave;

  @Override
  public void beforeAll(Class<?> testClass) throws Exception {

  }

  @Override
  public void beforeEach(Method testMethod) throws Exception {
    meecrowave = new Meecrowave(new Meecrowave.Builder() {
      {
        randomHttpPort();
        setHost(localeIP().get());
        setTomcatScanning(true);
        setTomcatAutoSetup(false);
        setHttp2(true);
      }
    }).bake();

    //mapping to TB Vars
    setProperty(NetworkFunctions.SERVER_WEBAPP, "/");
    setProperty(NetworkFunctions.SERVER_IP, meecrowave.getConfiguration().getHost());
    setProperty(NetworkFunctions.SERVER_PORT, String.valueOf(meecrowave.getConfiguration().getHttpPort()));
  }

  @Override
  public void afterEach(Method testMethod) throws Exception {
    meecrowave.close();
  }

  @Override
  public void afterAll(Class<?> testClass) throws Exception {

  }
}
