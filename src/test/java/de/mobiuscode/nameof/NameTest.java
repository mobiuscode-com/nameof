/**
 * The MIT License
 *
 * <p>Copyright 2022 MobiusCode GmbH.
 *
 * <p>Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * <p>The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * <p>THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.mobiuscode.nameof;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NameTest {

  @Test
  void of_validFieldsWithGetters_expectReturnsFieldNames() {
    assertEquals("myField", Name.of(TestClass.class, TestClass::getMyField));
    assertEquals("anotherField", Name.of(TestClass.class, TestClass::isAnotherField));
    assertEquals("somethingElse", Name.of(TestClass.class, TestClass::getSomethingElse));
    assertEquals("yetAnotherField", Name.of(TestClass.class, TestClass::getYetAnotherField));
    assertEquals("byteField", Name.of(TestClass.class, TestClass::getByteField));
    assertEquals("shortField", Name.of(TestClass.class, TestClass::getShortField));
    assertEquals("someMethod", Name.of(TestClass.class, TestClass::someMethod));
  }

  @Test
  void of_nullParameters_expectThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> Name.of(null, TestClass::someMethod));
    assertThrows(IllegalArgumentException.class, () -> Name.of(TestClass.class, null));
  }

  private static class TestClass {
    private String myField;
    private boolean anotherField;
    private Boolean yetAnotherField;

    private int somethingElse;
    private byte byteField;
    private Short shortField;

    public TestClass() {}

    public String getMyField() {
      return myField;
    }

    public boolean isAnotherField() {
      return anotherField;
    }

    public int getSomethingElse() {
      return somethingElse;
    }

    public Boolean getYetAnotherField() {
      return yetAnotherField;
    }

    public byte getByteField() {
      return byteField;
    }

    public Short getShortField() {
      return shortField;
    }

    public int someMethod() {
      return 0;
    }
  }
}
