package org.rapidpm.vaadin.addons.testbench.junit5.pageobject.vaadin;

import org.rapidpm.vaadin.addons.testbench.junit5.pageobject.WebElementUtil;
import xxx.com.github.webdriverextensions.WebComponent;

public abstract class VaadinWebComponent extends WebComponent {
  private static final int TIMEOUT_IN_SECONDS = 10;

  @Override
  public void click() {
    waitForVaadin();
    super.click();
  }

  public abstract String getCaption();

  public void waitForVaadin() {
    VaadinConditions.waitForVaadin(TIMEOUT_IN_SECONDS, getWrappedDriver());
  }

  @Override
  public boolean isEnabled() {
    return WebElementUtil.hasNotClass("v-disabled", this);
  }
}
