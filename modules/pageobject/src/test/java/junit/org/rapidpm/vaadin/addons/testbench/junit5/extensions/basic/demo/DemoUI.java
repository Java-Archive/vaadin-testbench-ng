package junit.org.rapidpm.vaadin.addons.testbench.junit5.extensions.basic.demo;

import org.vaadin.addonhelpers.AbstractTest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;


public class DemoUI extends AbstractTest {

  public static final String BUTTON_ID = "buttonID";

  @Override
  public Component getTestComponent() {
    final Button button = new Button();
    button.setId(BUTTON_ID);
    button.setCaption(BUTTON_ID);
    button.addClickListener(e -> System.out.println("e = " + e));
    return button;
  }
}
