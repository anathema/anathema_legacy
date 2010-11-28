package net.sf.anathema.development.test.database;

import java.util.HashMap;
import java.util.Map;

public class GenderMap {

  private final Map<Gender, Person> map = new HashMap<Gender, Person>();

  public void put(Gender gender, Person person) {
    map.put(gender, person);
  }

  public Person get(Gender gender) {
    return map.get(gender);
  }
}