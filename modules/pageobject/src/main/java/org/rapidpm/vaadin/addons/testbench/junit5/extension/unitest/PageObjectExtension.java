package org.rapidpm.vaadin.addons.testbench.junit5.extension.unitest;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.openqa.selenium.WebDriver;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.vaadin.addons.testbench.junit5.pageobject.AbstractPageObject;
import org.rapidpm.vaadin.addons.testbench.junit5.pageobject.PageObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.rapidpm.vaadin.addons.webdriver.junit5.WebdriverExtensionFunctions.webdriver;


/**
 *
 */
public class PageObjectExtension implements ParameterResolver, HasLogger {

  @Override
  public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    final Class<?> type = parameterContext.getParameter().getType();
    return PageObject.class.isAssignableFrom(type) ;
  }

  @Override
  public AbstractPageObject resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    logger().info("PageObjectExtension - resolveParameter");
    final WebDriver webDriver  = webdriver().apply(extensionContext);
    Class<?>        pageObject = parameterContext.getParameter().getType();
    try {
      Constructor<?> constructor =
          pageObject.getConstructor(WebDriver.class);
      return AbstractPageObject.class.cast(constructor.newInstance(webDriver));
    } catch (NoSuchMethodException
        | IllegalAccessException
        | InstantiationException
        | InvocationTargetException e) {
      e.printStackTrace();
      throw new ParameterResolutionException("was not able to create PageObjectInstance ", e);
    }
  }


}
