package org.rapidpm.vaadin.addons.testbench.junit5.pageobject;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.*;
import org.openqa.selenium.WebDriver;

/**
 *
 */
public abstract class AbstractVaadinPageObject
    extends AbstractPageObject
    implements VaadinPageObject {

  private TestBenchTestCase testCase = new TestBenchTestCase() { };

  public AbstractVaadinPageObject(WebDriver webdriver) {
    super(webdriver);
    //vaadin specific init
    testCase.setDriver(webdriver);
    setDriver(testCase.getDriver());
  }

  public WithID<TextFieldElement> textField() {
    return id -> testCase.$(TextFieldElement.class).id(id);
  }

  public WithID<PasswordFieldElement> passwordField() {
    return id -> testCase.$(PasswordFieldElement.class).id(id);
  }

  public WithID<ButtonElement> btn() {
    return id -> testCase.$(ButtonElement.class).id(id);
  }

  public WithID<LabelElement> label() {
    return id -> testCase.$(LabelElement.class).id(id);
  }

  public WithID<GridElement> grid() {
    return id -> testCase.$(GridElement.class).id(id);
  }

  public WithID<ComboBoxElement> comboBox() {
    return id -> testCase.$(ComboBoxElement.class).id(id);
  }

  public WithID<DateFieldElement> dateField() {
    return id -> testCase.$(DateFieldElement.class).id(id);
  }

  public WithID<FormLayoutElement> formLayout() {
    return id -> testCase.$(FormLayoutElement.class).id(id);
  }

  public WithID<CssLayoutElement> cssLayout() {
    return id -> testCase.$(CssLayoutElement.class).id(id);
  }

  public WithID<HorizontalLayoutElement> horizontalLayout() {
    return id -> testCase.$(HorizontalLayoutElement.class).id(id);
  }

  public WithID<VerticalLayoutElement> verticalLayout() {
    return id -> testCase.$(VerticalLayoutElement.class).id(id);
  }


}
