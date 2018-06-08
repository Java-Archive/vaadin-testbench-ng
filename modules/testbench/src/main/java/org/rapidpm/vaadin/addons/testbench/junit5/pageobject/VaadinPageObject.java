package org.rapidpm.vaadin.addons.testbench.junit5.pageobject;

import java.util.function.Supplier;

/**
 *
 */
public interface VaadinPageObject extends PageObject {

  default Supplier<String> urlRestartApp() {
    return () -> url().get() + "?restartApplication";
  }

  default Supplier<String> urlDebugApp() {
    return () -> url().get() + "?debug";
  }


}
