package junit.org.rapidpm.vaadin.addons.testbench.junit5.extensions.basic;

import junit.org.rapidpm.vaadin.addons.testbench.junit5.extensions.basic.demo.BasicTestPageObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.unittest.VaadinWebUnitTest;

/**
 *
 */
@VaadinWebUnitTest
@ExtendWith(RapidPMExtension.class)
class BasicUnitTest {

  @Test
  void test001(BasicTestPageObject pageObject) {
    pageObject.loadPage();
    pageObject.button.get().click();
    pageObject.screenshot();
  }
}
