package de.mobiuscode.nameof;

import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

public final class Name {

  public static final String GETTER_PREFIX_GET = "get";
  public static final String GETTER_PREFIX_IS = "is";

  private Name() {}

  /**
   * programmatically returns the name of the type's field specified by the given getter method
   *
   * <p>Example:
   *
   * <pre>
   *   Name.of(MyClass.class, MyClass::getFoobar);
   * </pre>
   *
   * @param typeClass
   * @param entityFieldGetter
   * @param <T>
   * @return
   */
  public static <T> String of(Class<T> typeClass, Function<T, ?> entityFieldGetter) {
    if (typeClass == null)
      throw new IllegalArgumentException("unable to determine name of null class");
    if (entityFieldGetter == null)
      throw new IllegalArgumentException("unable to determine name of null getter reference");
    try {
      var fieldName = new StringBuilder();

      var proxyFactory = new ProxyFactory();
      proxyFactory.setSuperclass(typeClass);
      var fakeEntity =
          (T)
              proxyFactory.create(
                  new Class<?>[0],
                  new Object[0],
                  ((o, method, method1, objects) -> {
                    fieldName.append(getterToFieldName(method.getName()));
                    return getValidReturnValue(method);
                  }));

      entityFieldGetter.apply(fakeEntity);

      return fieldName.toString();
    } catch (InvocationTargetException
        | NoSuchMethodException
        | InstantiationException
        | IllegalAccessException e) {
      throw new IllegalArgumentException(
          "unable to determine name of field for provided getter", e);
    }
  }

  private static Object getValidReturnValue(Method method) {
    Class<?> returnType = method.getReturnType();
    if (returnType.isPrimitive()) {
      if (returnType == boolean.class || returnType == Boolean.class) {
        return false;
      }
      if (returnType == byte.class || returnType == Byte.class) {
        return (byte) 0;
      }
      if (returnType == char.class || returnType == Character.class) {
        return (char) 0;
      }
      if (returnType == short.class || returnType == Short.class) {
        return (short) 0;
      }
      if (returnType == long.class || returnType == Long.class) {
        return (long) 0;
      }
      return 0;
    }

    return null;
  }

  private static String getterToFieldName(String methodName) {
    int cutOffLength = 0;
    if (methodName.startsWith(GETTER_PREFIX_GET)) {
      cutOffLength = 3;
    } else if (methodName.startsWith(GETTER_PREFIX_IS)) {
      cutOffLength = 2;
    }
    if (methodName.length() <= cutOffLength + 1) {
      throw new IllegalArgumentException(
          String.format(
              "method name \"%s\" is not according to convention for a getter", methodName));
    }
    return Character.toLowerCase(methodName.charAt(cutOffLength))
        + methodName.substring(cutOffLength + 1);
  }
}
