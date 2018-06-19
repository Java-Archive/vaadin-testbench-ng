package junit.org.rapidpm.vaadin.addons.testbench;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rapidpm.frp.matcher.Case;
import org.rapidpm.vaadin.addons.webdriver.BrowserDriverFunctions;

import java.util.Properties;

import static java.lang.Boolean.TRUE;
import static java.lang.String.valueOf;
import static org.junit.jupiter.api.Assertions.fail;
import static org.rapidpm.frp.matcher.Case.matchCase;
import static org.rapidpm.frp.model.Result.failure;
import static org.rapidpm.frp.model.Result.success;

public class BrowserDriverFunctionsTest {

  @Test
  @DisplayName("test reading properties")
  void test001() {
    Properties properties = BrowserDriverFunctions.propertyReader()
                                                  .apply(BrowserDriverFunctions.CONFIG_FOLDER + "config").get();


    final String unittestingTarget = valueOf(properties.get("unittesting.target")).trim();
    Case
        .match(
            matchCase(() -> failure("no matching unittesting.target..")),
            matchCase(() -> "locale".equals(unittestingTarget), () -> success(TRUE)),
            matchCase(() -> "selenoid.rapidpm.org".equals(unittestingTarget), () -> success(TRUE)),
            matchCase(() -> "selenoid-server".equals(unittestingTarget), () -> success(TRUE))
        )
        .ifAbsent(fail("no expected property value found for unittesting.target.. " + unittestingTarget));

  }
}
