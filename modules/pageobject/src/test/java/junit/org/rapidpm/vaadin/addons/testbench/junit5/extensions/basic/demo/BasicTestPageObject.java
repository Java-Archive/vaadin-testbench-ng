package junit.org.rapidpm.vaadin.addons.testbench.junit5.extensions.basic.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.rapidpm.vaadin.addons.testbench.junit5.pageobject.AbstractPageObject;

public class BasicTestPageObject extends AbstractPageObject {

  public BasicTestPageObject(WebDriver webDriver) {
    super(webDriver);
  }

  @FindBy(id = DemoUI.COMPONENT_ID)
  private MyComponentTestComponent component;

  public MyComponentTestComponent getComponent() {
    return component;
  }
}
