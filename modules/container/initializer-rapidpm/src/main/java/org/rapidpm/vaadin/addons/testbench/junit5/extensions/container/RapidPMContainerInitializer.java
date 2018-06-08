package org.rapidpm.vaadin.addons.testbench.junit5.extensions.container;

import com.google.auto.service.AutoService;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.microservice.Main;

import java.lang.reflect.Method;

@AutoService(ContainerInitializer.class)
public class RapidPMContainerInitializer implements ContainerInitializer, HasLogger {

  @Override
  public void beforeEach(Method testMethod) throws Exception {
    Main.deploy();
  }

  @Override
  public void afterEach(Method testMethod) throws Exception {
    Main.stop();
  }

  @Override
  public void afterAll(Class<?> testClass) throws Exception {
    //nothing
  }

  @Override
  public void beforeAll(Class<?> testClass) throws Exception {
  }
}
