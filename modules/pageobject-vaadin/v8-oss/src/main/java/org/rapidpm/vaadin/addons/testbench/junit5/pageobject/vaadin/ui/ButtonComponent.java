package org.rapidpm.vaadin.addons.testbench.junit5.pageobject.vaadin.ui;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.rapidpm.vaadin.addons.testbench.junit5.pageobject.vaadin.VaadinWebComponent;

public class ButtonComponent extends VaadinWebComponent {

  @FindBy(className = "v-button-caption")
  private WebElement caption;
  @FindBy(className = "v-icon")
  private WebElement icon;

  @Override
  public String getCaption() {
    return caption.getText();
  }

  public WebElement getIcon() {
    try {
      icon.isDisplayed();
      return icon;
    } catch (NoSuchElementException e) {
      return null;
    }
  }
}
