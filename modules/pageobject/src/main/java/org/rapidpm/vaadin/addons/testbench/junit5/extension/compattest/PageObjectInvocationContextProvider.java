package org.rapidpm.vaadin.addons.testbench.junit5.extension.compattest;

import static java.util.Collections.singletonList;
import static org.rapidpm.vaadin.addons.webdriver.BrowserDriverFunctions.webDriverInstances;
import static org.rapidpm.vaadin.addons.webdriver.WebDriverFunctions.webdriverName;
import static org.rapidpm.vaadin.addons.webdriver.junit5.WebdriverExtensionFunctions.storeWebDriver;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;
import org.openqa.selenium.WebDriver;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.frp.functions.CheckedFunction;
import org.rapidpm.frp.model.Result;
import org.rapidpm.vaadin.addons.testbench.junit5.pageobject.PageObject;

/**
 *
 */
public class PageObjectInvocationContextProvider implements TestTemplateInvocationContextProvider, HasLogger {
  @Override
  public boolean supportsTestTemplate(ExtensionContext context) {
    return true;
  }

  @Override
  public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {

    logger().info("provideTestTemplateInvocationContexts");

    return webDriverInstances()
        .stream()
        .map(this::invocationContext)
        .peek(po -> {
          logger().info("peek - page object -> setting as webDriver into Store ");
          storeWebDriver().accept(context, po.webdriver());
        })
        .map(e -> (MyTestTemplateInvocationContext) e);
  }


  public interface MyTestTemplateInvocationContext extends TestTemplateInvocationContext {
    WebDriver webdriver();
  }


  private MyTestTemplateInvocationContext invocationContext(Supplier<WebDriver> webdriverSupplier) {
    return new MyTestTemplateInvocationContext() {

      private WebDriver webDriver;

      @Override
      public WebDriver webdriver() {
        if (webDriver == null) {
          webDriver = webdriverSupplier.get();
        }
        return webDriver;
      }

      @Override
      public String getDisplayName(int invocationIndex) {
        return webdriverName().apply(webdriver());
      }

      @Override
      public List<Extension> getAdditionalExtensions() {
        return singletonList(new ParameterResolver() {
          @Override
          public boolean supportsParameter(ParameterContext parameterContext,
                                           ExtensionContext extensionContext) {
            final Class<?> type = parameterContext.getParameter().getType();
            return PageObject.class.isAssignableFrom(type);
          }

          @Override
          public Object resolveParameter(ParameterContext parameterContext,
                                         ExtensionContext extensionContext) {

            Class<?> pageObjectClass = parameterContext
                .getParameter()
                .getType();

            final Result<PageObject> po = ((CheckedFunction<Class<?>, PageObject>) aClass -> {
              final Constructor<?> constructor = pageObjectClass.getConstructor(WebDriver.class);
              return PageObject.class.cast(constructor.newInstance(webdriver()));
            })
                .apply(pageObjectClass);

            po.ifPresentOrElse(
                success -> logger().fine("pageobject of type " + pageObjectClass.getSimpleName() + " was created with " + webdriverName().apply(webdriver())),
                failed -> logger().warning("was not able to create PageObjectInstance " + failed)
            );
            po.ifAbsent(() -> {
              throw new ParameterResolutionException("was not able to create PageObjectInstance of type " + pageObjectClass);
            });
            return po.get();
          }
        });
      }
    };
  }
}
