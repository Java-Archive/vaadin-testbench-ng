package org.rapidpm.vaadin.addons.testbench.junit5.extensions.compattest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.container.ServletContainerExtension;
import org.rapidpm.vaadin.addons.testbench.junit5.extension.compattest.PageObjectInvocationContextProvider;
import org.rapidpm.vaadin.addons.testbench.junit5.extension.compattest.PageObjectWebDriverCleanerExtension;
import org.rapidpm.vaadin.addons.testbench.junit5.extensions.unittest.ConvertWebdriverTestExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(ServletContainerExtension.class)
@ExtendWith(PageObjectInvocationContextProvider.class)

//convert Driver
@ExtendWith(ConvertWebdriverTestExtension.class)

@ExtendWith(PageObjectWebDriverCleanerExtension.class)
public @interface VaadinWebCompatTest {
}
