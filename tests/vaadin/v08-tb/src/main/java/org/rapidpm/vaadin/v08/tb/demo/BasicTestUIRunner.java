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
package org.rapidpm.vaadin.v08.tb.demo;

import org.rapidpm.vaadin.nano.CoreUIService;

import static java.lang.System.setProperty;
import static org.rapidpm.vaadin.nano.CoreUI.COMPONENT_SUPPLIER_TO_USE;

public class BasicTestUIRunner {
  private BasicTestUIRunner() {
  }

  public static void main(String[] args) {
    setProperty(COMPONENT_SUPPLIER_TO_USE, BasicTestUISupplier.class.getName());
    new CoreUIService().startup();
  }

}
