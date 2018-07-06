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
