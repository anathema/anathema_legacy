package net.sf.anathema.lib.util;

import java.util.List;
import java.util.Random;

public class RandomUtilities {

  public static final Random RANDOM = new Random();

  public static int nextPercent() {
    return RANDOM.nextInt(100);
  }

  public static <T> T choose(T[] objects) {
    return objects[RandomUtilities.RANDOM.nextInt(objects.length)];
  }

  public static <T> T choose(List<T> objects) {
    return objects.get(RandomUtilities.RANDOM.nextInt(objects.size()));
  }
}