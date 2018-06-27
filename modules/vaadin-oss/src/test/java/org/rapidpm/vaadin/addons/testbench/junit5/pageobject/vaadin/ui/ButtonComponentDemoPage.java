package org.rapidpm.vaadin.addons.testbench.junit5.pageobject.vaadin.ui;

import java.util.function.Supplier;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.rapidpm.vaadin.addons.testbench.junit5.pageobject.vaadin.VaadinPageObject;

public class ButtonComponentDemoPage extends VaadinPageObject {

  @FindBy(id = "button-1")
  private ButtonComponent button1;
  @FindBy(id = "button-2")
  private ButtonComponent button2;
  @FindBy(id = "click-count")
  private WebElement clickCount;

  public ButtonComponentDemoPage(WebDriver webdriver) {
    super(webdriver);
  }

  @Override
  public Supplier<String> webapp() {
    return () -> ButtonComponentDemo.class.getName();
  }

  public ButtonComponent getButton1() {
    return button1;
  }

  public ButtonComponent getButton2() {
    return button2;
  }

  public int getClickCount() {
    return Integer.valueOf(clickCount.getText());
  }
}
