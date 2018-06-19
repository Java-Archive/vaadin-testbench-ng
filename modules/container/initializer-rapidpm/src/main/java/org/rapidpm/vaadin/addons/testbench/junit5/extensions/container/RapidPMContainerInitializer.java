package org.rapidpm.vaadin.addons.testbench.junit5.extensions.container;

import com.google.auto.service.AutoService;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.dependencies.core.net.PortUtils;
import org.rapidpm.microservice.Main;
import org.rapidpm.microservice.MainUndertow;

import java.lang.reflect.Method;

import static org.rapidpm.vaadin.addons.testbench.junit5.extensions.container.NetworkFunctions.localeIP;

@AutoService(ContainerInitializer.class)
public class RapidPMContainerInitializer implements ContainerInitializer, HasLogger {

  @Override
  public void beforeEach(Method testMethod) throws Exception {
    String          localIP   = localeIP().get();
    final PortUtils portUtils = new PortUtils();
    System.setProperty(MainUndertow.REST_HOST_PROPERTY, localIP);
    System.setProperty(MainUndertow.SERVLET_HOST_PROPERTY, localIP);
    System.setProperty(MainUndertow.REST_PORT_PROPERTY, portUtils.nextFreePortForTest() + "");
    System.setProperty(MainUndertow.SERVLET_PORT_PROPERTY, portUtils.nextFreePortForTest() + "");

    //mapping to TB Vars


    Main.deploy();
  }

  @Override
  public void afterEach(Method testMethod) throws Exception {
    Main.stop();

    // remove all Properties..

  }

  @Override
  public void afterAll(Class<?> testClass) throws Exception {
    // nothing
  }

  @Override
  public void beforeAll(Class<?> testClass) throws Exception {
    // nothing
  }
}
