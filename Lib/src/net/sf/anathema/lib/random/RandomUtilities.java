package net.sf.anathema.lib.random;

import java.util.Random;

public class RandomUtilities {

  public static final Random RANDOM = new Random();

  public static int nextPercent() {
    return RANDOM.nextInt(100);
  }

  public static <T> T choose(T[] objects) {
    return objects[RandomUtilities.RANDOM.nextInt(objects.length)];
  }
}