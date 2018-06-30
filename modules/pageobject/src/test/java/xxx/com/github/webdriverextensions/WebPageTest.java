/**
 * Copyright © 2017 Sven Ruppert (sven.ruppert@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xxx.com.github.webdriverextensions;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class WebPageTest {

  private WebPage opendPage    = new WebPage() {

    @Override
    public void open(Object... arguments) {
      // NoOp - just unit test
    }

    @Override
    public void assertIsOpen(Object... arguments) throws AssertionError {
      // NoOP - page is open
    }

  };
  private WebPage notOpendPage = new WebPage() {

    @Override
    public void open(Object... arguments) {
      // NoOp - just unit test
    }

    @Override
    public void assertIsOpen(Object... arguments) throws AssertionError {
      throw new AssertionError();
    }
  };

  @Test
  public void testAssertIsNotOpen_notOpendPage() {
    try {
      notOpendPage.assertIsNotOpen();
    } catch (AssertionError e) {
      fail("No AssertionError expected");
    }
  }

  @Test
  public void testAssertIsNotOpen_opendPage() {
    Assertions.assertThrows(AssertionError.class, () -> opendPage.assertIsNotOpen());
  }

  @Test
  public void testAssertIsOpen_notOpendPage() {
    Assertions.assertThrows(AssertionError.class, () -> notOpendPage.assertIsOpen());
  }

  @Test
  public void testAssertIsOpen_opendPage() {
    try {
      opendPage.assertIsOpen();
    } catch (AssertionError e) {
      fail("No AssertionError expected");
    }
  }
}
