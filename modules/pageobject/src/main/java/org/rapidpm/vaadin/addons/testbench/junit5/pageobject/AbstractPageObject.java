package org.rapidpm.vaadin.addons.testbench.junit5.pageobject;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPageObject implements PageObject {


  public AbstractPageObject(WebDriver webdriver) {
    setDriver(webdriver);
  }

  private WebDriver driver;

  @Override
  public WebDriver getDriver() {
    return driver;
  }

  @Override
  public void setDriver(WebDriver driver) {
    this.driver = driver;
  }

}
