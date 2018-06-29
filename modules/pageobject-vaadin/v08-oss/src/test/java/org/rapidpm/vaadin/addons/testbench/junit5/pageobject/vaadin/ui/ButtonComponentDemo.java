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

import org.vaadin.addonhelpers.AbstractTest;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ButtonComponentDemo extends AbstractTest {
  public static final String BUTTON_1 = "button-1";
  public static final String BUTTON_2 = "button-2";
  public static final String CLICK_COUNT = "click-count";

  private int clickcount = 0;
  private Label clickCountLabel;

  @Override
  public Component getTestComponent() {

    Button button1 = new Button("Button 1", this::inc);
    button1.setId(BUTTON_1);

    Button button2 = new Button("Button 2", VaadinIcons.AIRPLANE);
    button2.setId(BUTTON_2);
    button2.setEnabled(false);
    clickCountLabel = new Label("0");
    clickCountLabel.setId(CLICK_COUNT);
    return new VerticalLayout(button1, button2, clickCountLabel);
  }

  public void inc(Button.ClickEvent event) {
    clickcount++;
    clickCountLabel.setValue(String.valueOf(clickcount));
  }
}
