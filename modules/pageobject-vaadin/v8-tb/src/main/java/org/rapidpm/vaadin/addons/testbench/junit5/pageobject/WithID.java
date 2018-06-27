package org.rapidpm.vaadin.addons.testbench.junit5.pageobject;

import com.vaadin.testbench.elementsbase.AbstractElement;

/**
 *
 */
@FunctionalInterface
public interface WithID<T extends AbstractElement> {
  T id(String id);
}
