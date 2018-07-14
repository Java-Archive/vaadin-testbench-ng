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

import static org.rapidpm.vaadin.addons.testbench.junit5.pageobject.vaadin.ui.ButtonComponentDemo.BUTTON_1;
import static org.rapidpm.vaadin.addons.testbench.junit5.pageobject.vaadin.ui.ButtonComponentDemo.BUTTON_2;
import static org.rapidpm.vaadin.addons.testbench.junit5.pageobject.vaadin.ui.ButtonComponentDemo.CLICK_COUNT;
import java.util.function.Supplier;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.container.ContainerInfo;
import org.rapidpm.vaadin.addons.testbench.junit5.pageobject.vaadin.AbstractVaadinPageObject;

public class ButtonComponentDemoPageObjectAbstract extends AbstractVaadinPageObject {

  @FindBy(id = BUTTON_1)
  private ButtonWebComponent button1;
  @FindBy(id = BUTTON_2)
  private ButtonWebComponent button2;
  @FindBy(id = CLICK_COUNT)
  private WebElement         clickCount;

  public ButtonComponentDemoPageObjectAbstract(WebDriver webdriver, ContainerInfo containerInfo) {
    super(webdriver, containerInfo);
  }

  @Override
  public Supplier<String> webapp() {
    return ButtonComponentDemo.class::getName;
  }

  public ButtonWebComponent getButton1() {
    return button1;
  }

  public ButtonWebComponent getButton2() {
    return button2;
  }

  public int getClickCount() {
    return Integer.valueOf(clickCount.getText());
  }
}
