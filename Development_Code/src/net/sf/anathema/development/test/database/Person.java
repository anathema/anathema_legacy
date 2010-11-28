package net.sf.anathema.development.test.database;

import org.apache.commons.lang.builder.EqualsBuilder;

public class Person {

  private final Gender gender;
  private final String name;

  public Person(String name, Gender gender) {
    this.name = name;
    this.gender = gender;
  }

  public Gender getGender() {
    return gender;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(obj, this);
  }
}