package junit.org.rapidpm.vaadin.addons.testbench.junit5.extensions.basic;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.rapidpm.vaadin.addons.testbench.junit5.extension.compattest.WebCompatTest;
import junit.org.rapidpm.vaadin.addons.testbench.junit5.extensions.basic.demo.BasicTestPageObject;
import junit.org.rapidpm.vaadin.addons.testbench.junit5.extensions.basic.demo.PageObjectConfigExtension;

/**
 *
 */
@WebCompatTest
@ExtendWith(PageObjectConfigExtension.class)
class BasicCompatTest {

  @TestTemplate
  void testTemplate(BasicTestPageObject pageObject) {
    pageObject.loadPage();
    pageObject.button.get().click();
    pageObject.screenshot();
  }
}
