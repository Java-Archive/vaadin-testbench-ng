package org.rapidpm.vaadin.addons.testbench.junit5.pageobject.vaadin.ui;

import org.vaadin.addonhelpers.AbstractTest;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ButtonComponentDemo extends AbstractTest {
  private int clickcount = 0;
  private Label clickCountLabel;

  @Override
  public Component getTestComponent() {

    Button button1 = new Button("Button 1", event -> {
      this.inc(event);
    });
    button1.setId("button-1");

    Button button2 = new Button("Button 2", VaadinIcons.AIRPLANE);
    button2.setId("button-2");
    button2.setEnabled(false);
    clickCountLabel = new Label("0");
    clickCountLabel.setId("click-count");
    return new VerticalLayout(button1, button2, clickCountLabel);
  }

  public void inc(Button.ClickEvent event) {
    clickcount++;
    clickCountLabel.setValue(String.valueOf(clickcount));
  }
}
