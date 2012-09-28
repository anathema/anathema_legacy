package net.sf.anathema.initialization.reflections;

import java.util.Comparator;

public class ByWeightAnnotation implements Comparator<Class<?>> {
  @Override
  public int compare(Class<?> class1, Class<?> class2) {
    int weight1 = getWeight(class1);
    int weight2 = getWeight(class2);
    return weight1 - weight2;
  }

  private int getWeight(Class<?> class1) {
    if (!class1.isAnnotationPresent(Weight.class)) {
      return Integer.MAX_VALUE;
    }
    Weight annotation = class1.getAnnotation(Weight.class);
    return annotation.weight();
  }
}
