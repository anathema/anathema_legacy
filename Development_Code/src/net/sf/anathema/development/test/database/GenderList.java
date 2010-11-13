package net.sf.anathema.development.test.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenderList {

  private final List<Gender> list = new ArrayList<Gender>();

  public void add(Gender gender) {
    list.add(gender);
  }

  public int frequency(Gender gender) {
    return Collections.frequency(list, gender);
  }
}