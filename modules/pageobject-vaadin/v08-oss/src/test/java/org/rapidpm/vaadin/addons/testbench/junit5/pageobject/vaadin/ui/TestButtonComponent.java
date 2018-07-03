/**
 * Copyright © 2017 Sven Ruppert (sven.ruppert@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
  void test001(ButtonComponentDemoPageObjectAbstract page) {
    page.loadPage();

    assertThat(page.getButton1().getCaption(), is("Button 1"));
    assertThat(page.getButton2().getCaption(), is("Button 2"));
  }

  @Test
  void test002(ButtonComponentDemoPageObjectAbstract page) {
    page.loadPage();

    assertThat(page.getButton1().getIcon(), is(nullValue()));
    assertThat(page.getButton2().getIcon(), is(not(nullValue())));
  }

  @Test
  void test003(ButtonComponentDemoPageObjectAbstract page) {
    page.loadPage();

    assertThat(page.getButton1().isEnabled(), is(true));
    assertThat(page.getButton2().isEnabled(), is(false));
  }

  @Test
  void test004(ButtonComponentDemoPageObjectAbstract page) {
    page.loadPage();
    assertThat(page.getClickCount(), is(0));
    page.getButton1().click();
    assertThat(page.getClickCount(), is(1));
    page.getButton2().click();
    assertThat(page.getClickCount(), is(1));
  }
}
