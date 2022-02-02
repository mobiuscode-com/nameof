# nameof()
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.mobiuscode.nameof/nameof/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.mobiuscode.nameof/nameof)

A `Java` library to programmatically return the name of fields similar to the C# `nameof` expression

## How to use?
The library is part of the Central Repository and can simply be used like this:

### Maven
```xml
<dependency>
    <groupId>de.mobiuscode.nameof</groupId>
    <artifactId>nameof</artifactId>
    <version>1.0</version>
</dependency>
```

### Gradle
```groovy
implementation 'de.mobiuscode.nameof:nameof:1.0'
```

## Example

If you have a Java Bean like this:
```java
public class User {
  private String userName;

  private String firstName;
  private String lastName;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
```

you can use this method to get the name of a field by its getter:

```java
Name.of(User.class, User::getFirstName);
```

This will return the string `"firstName"`, just like the C# expression `nameof(firstName)` would do.

This is particularly handy in case you are refactoring the name of that field, because then the name of the getter would also be adjusted and with it the `Name.of()` method would return the new field name.
