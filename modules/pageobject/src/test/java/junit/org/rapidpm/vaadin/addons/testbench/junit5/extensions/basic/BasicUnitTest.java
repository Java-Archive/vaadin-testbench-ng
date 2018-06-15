package junit.org.rapidpm.vaadin.addons.testbench.junit5.extensions.basic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rapidpm.vaadin.addons.testbench.junit5.extension.unitest.WebUnitTest;
import junit.org.rapidpm.vaadin.addons.testbench.junit5.extensions.basic.demo.BasicTestPageObject;
import junit.org.rapidpm.vaadin.addons.testbench.junit5.extensions.basic.demo.PageObjectConfigExtension;

@WebUnitTest
@ExtendWith(PageObjectConfigExtension.class)
class BasicUnitTest {

  @Test
  void test001(BasicTestPageObject pageObject) {

    pageObject.loadPage();
    pageObject.button.get().click();
    pageObject.screenshot();
  }
}
