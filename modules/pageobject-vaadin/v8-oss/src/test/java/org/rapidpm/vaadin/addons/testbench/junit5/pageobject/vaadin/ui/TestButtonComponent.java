package org.rapidpm.vaadin.addons.testbench.junit5.pageobject.vaadin.ui;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rapidpm.vaadin.addons.testbench.junit5.extension.unitest.WebUnitTest;

@WebUnitTest
class TestButtonComponent {

  @BeforeEach
  void setUp() throws Exception {}

  @Test
  void test001(ButtonComponentDemoPage page) {
    page.loadPage();

    assertThat(page.getButton1().getCaption(), is("Button 1"));
    assertThat(page.getButton2().getCaption(), is("Button 2"));
  }

  @Test
  void test002(ButtonComponentDemoPage page) {
    page.loadPage();

    assertThat(page.getButton1().getIcon(), is(nullValue()));
    assertThat(page.getButton2().getIcon(), is(not(nullValue())));
  }

  @Test
  void test003(ButtonComponentDemoPage page) {
    page.loadPage();

    assertThat(page.getButton1().isEnabled(), is(true));
    assertThat(page.getButton2().isEnabled(), is(false));
  }

  @Test
  void test004(ButtonComponentDemoPage page) {
    page.loadPage();
    assertThat(page.getClickCount(), is(0));
    page.getButton1().click();
    assertThat(page.getClickCount(), is(1));
    page.getButton2().click();
    assertThat(page.getClickCount(), is(1));
  }
}
