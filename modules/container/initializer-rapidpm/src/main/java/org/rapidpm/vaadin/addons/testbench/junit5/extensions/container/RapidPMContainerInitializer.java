package org.rapidpm.vaadin.addons.testbench.junit5.extensions.container;

import com.google.auto.service.AutoService;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.dependencies.core.net.PortUtils;
import org.rapidpm.microservice.Main;
import org.rapidpm.microservice.MainUndertow;

import java.lang.reflect.Method;

import static java.lang.System.getProperty;
import static java.lang.System.setProperty;
import static org.rapidpm.vaadin.addons.testbench.junit5.extensions.container.NetworkFunctions.localeIP;

@AutoService(ContainerInitializer.class)
public class RapidPMContainerInitializer implements ContainerInitializer, HasLogger {

  @Override
  public void beforeEach(Method testMethod) throws Exception {
    String          localIP   = localeIP().get();
    final PortUtils portUtils = new PortUtils();
    setProperty(MainUndertow.REST_HOST_PROPERTY, localIP);
    setProperty(MainUndertow.SERVLET_HOST_PROPERTY, localIP);
    setProperty(MainUndertow.REST_PORT_PROPERTY, portUtils.nextFreePortForTest() + "");
    setProperty(MainUndertow.SERVLET_PORT_PROPERTY, portUtils.nextFreePortForTest() + "");

    //mapping to TB Vars
    setProperty(NetworkFunctions.SERVER_WEBAPP, MainUndertow.MYAPP);
    setProperty(NetworkFunctions.SERVER_IP, getProperty(MainUndertow.SERVLET_HOST_PROPERTY));
    setProperty(NetworkFunctions.SERVER_PORT, getProperty(MainUndertow.SERVLET_PORT_PROPERTY));


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
