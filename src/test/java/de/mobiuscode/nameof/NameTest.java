package de.mobiuscode.nameof;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NameTest {

  @Test
  void of() {
    assertEquals("myField", Name.of(TestClass.class, TestClass::getMyField));
    assertEquals("anotherField", Name.of(TestClass.class, TestClass::isAnotherField));
    assertEquals("somethingElse", Name.of(TestClass.class, TestClass::getSomethingElse));
    assertEquals("yetAnotherField", Name.of(TestClass.class, TestClass::getYetAnotherField));
    assertEquals("byteField", Name.of(TestClass.class, TestClass::getByteField));
    assertEquals("shortField", Name.of(TestClass.class, TestClass::getShortField));
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
  }
}
