package org.rapidpm.vaadin.addons.testbench.junit5.extension.unitest;

import static org.rapidpm.vaadin.addons.webdriver.junit5.WebdriverExtensionFunctions.webdriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.vaadin.addons.testbench.junit5.pageobject.AbstractPageObject;
import org.rapidpm.vaadin.addons.testbench.junit5.pageobject.PageObject;

import xxx.com.github.webdriverextensions.WebDriverExtensionFieldDecorator;

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
    Class<?>        pageObjectClass = parameterContext.getParameter().getType();
    try {
      Constructor<?> constructor =
          pageObjectClass.getConstructor(WebDriver.class);
         AbstractPageObject page = AbstractPageObject.class
               .cast(constructor.newInstance(webDriver));
         PageFactory.initElements(
               new WebDriverExtensionFieldDecorator(webDriver), page);
         return page;
    } catch (NoSuchMethodException
        | IllegalAccessException
        | InstantiationException
        | InvocationTargetException e) {
      e.printStackTrace();
      throw new ParameterResolutionException("was not able to create PageObjectInstance ", e);
    }
  }


}
