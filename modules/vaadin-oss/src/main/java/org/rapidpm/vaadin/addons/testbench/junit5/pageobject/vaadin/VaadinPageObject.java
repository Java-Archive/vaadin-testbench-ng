package org.rapidpm.vaadin.addons.testbench.junit5.pageobject.vaadin;

import org.openqa.selenium.WebDriver;
import org.rapidpm.vaadin.addons.testbench.junit5.pageobject.AbstractPageObject;

public abstract class VaadinPageObject extends AbstractPageObject {

  public VaadinPageObject(WebDriver webdriver) {
    super(webdriver);
  }

  @Override
  public void loadPage() {
    super.loadPage();
    VaadinConditions.waitForVaadin(10, getDriver());
  }
}
