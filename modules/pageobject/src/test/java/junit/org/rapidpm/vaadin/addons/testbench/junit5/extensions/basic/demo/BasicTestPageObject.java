package junit.org.rapidpm.vaadin.addons.testbench.junit5.extensions.basic.demo;

import java.util.function.Supplier;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.rapidpm.vaadin.addons.testbench.junit5.pageobject.AbstractPageObject;

/**
 *
 */
public class BasicTestPageObject extends AbstractPageObject {

  public BasicTestPageObject(WebDriver webDriver) {
    super(webDriver);
  }

  public Supplier<WebElement> button = () -> getDriver().findElement(By.id(DemoUI.BUTTON_ID));
}
