package junit.org.rapidpm.vaadin.v10.tb.demo;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestTemplate;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.compattest.VaadinWebCompatTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@VaadinWebCompatTest
public class VaadinAppCompatTest {

  @TestTemplate
  @DisplayName("Hello World - Click twice")
    //@Disabled("classloader challenges with Atmosphere")
  void test001(VaadinAppPageObject pageObject) {
    pageObject.loadPage();
    assertEquals(0, pageObject.clickCount());
    pageObject.click();
    assertEquals(1, pageObject.clickCount());
  }
}
