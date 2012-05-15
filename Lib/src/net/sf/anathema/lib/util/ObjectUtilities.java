package net.sf.anathema.lib.util;

import java.util.Arrays;

public class ObjectUtilities {

  public final static boolean equals(Object o1, Object o2) {
    return (o1 == null && o2 == null) || (o1 != null && o1.equals(o2));
  }

  public final static int getHashCode(Object... objects) {
    if (objects.length == 1) {
      return hashCode(objects[0]);
    }
    return Arrays.hashCode(objects);
  }

  private final static int hashCode(Object object) {
    return (object == null) ? 1 : object.hashCode();
  }
}