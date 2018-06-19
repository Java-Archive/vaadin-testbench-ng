package junit.org.rapidpm.vaadin.addons.testbench.junit5.extensions.basic;


import org.junit.jupiter.api.extension.*;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.microservice.MainUndertow;
import org.rapidpm.vaadin.addons.testbench.junit5.pageobject.PageObject;

public class RapidPMExtension implements BeforeAllCallback, BeforeEachCallback,
                                         AfterEachCallback, AfterAllCallback, HasLogger {


  @Override
  public void afterAll(ExtensionContext context) throws Exception {

  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {

  }

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {

  }

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {

    System.setProperty(PageObject.SERVER_WEBAPP, MainUndertow.MYAPP);
    System.setProperty(PageObject.SERVER_IP, System.getProperty(MainUndertow.SERVLET_HOST_PROPERTY));
    System.setProperty(PageObject.SERVER_PORT, System.getProperty(MainUndertow.SERVLET_PORT_PROPERTY));


  }
}
