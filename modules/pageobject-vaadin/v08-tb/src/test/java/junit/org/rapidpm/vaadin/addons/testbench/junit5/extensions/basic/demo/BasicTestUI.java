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
package junit.org.rapidpm.vaadin.addons.testbench.junit5.extensions.basic.demo;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

import static org.rapidpm.vaadin.addons.framework.ComponentIDGenerator.buttonID;

/**
 *
 */
public class BasicTestUI extends UI {

  public static final String BUTTON_ID = buttonID().apply(BasicTestUI.class, "buttonID");

  @Override
  protected void init(VaadinRequest request) {
    final Button button = new Button();
    button.setId(BasicTestUI.BUTTON_ID);
    button.setCaption(BUTTON_ID);
    button.addClickListener(e -> System.out.println("e = " + e));
    setContent(button);
  }
}
