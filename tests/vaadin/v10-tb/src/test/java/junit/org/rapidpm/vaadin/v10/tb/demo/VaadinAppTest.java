package junit.org.rapidpm.vaadin.v10.tb.demo;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rapidpm.vaadin.addons.testbench.junit5.extension.unitest.WebUnitTest;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.unittest.VaadinWebUnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@VaadinWebUnitTest
public class VaadinAppTest {

  @Test
  @DisplayName("Hello World - Click twice")
  //@Disabled("classloader challenges with Atmosphere")
  void test001(VaadinAppPageObject pageObject) {
    pageObject.loadPage();
    assertEquals(0, pageObject.clickCount());
    pageObject.click();
    assertEquals(1, pageObject.clickCount());
  }
}
