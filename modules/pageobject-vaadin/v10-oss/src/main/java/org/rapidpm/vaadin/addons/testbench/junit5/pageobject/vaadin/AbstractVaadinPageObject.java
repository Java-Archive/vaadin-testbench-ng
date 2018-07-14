package org.rapidpm.vaadin.addons.testbench.junit5.pageobject.vaadin;

import org.openqa.selenium.WebDriver;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.container.ContainerInfo;
import org.rapidpm.vaadin.addons.testbench.junit5.pageobject.AbstractPageObject;
import org.rapidpm.vaadin.addons.testbench.junit5.pageobject.GenericVaadinAppSpecific;

public class AbstractVaadinPageObject
    extends AbstractPageObject
    implements GenericVaadinAppSpecific {


  public AbstractVaadinPageObject(WebDriver webdriver, ContainerInfo containerInfo) {
    super(webdriver, containerInfo);
  }


}
