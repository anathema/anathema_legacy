package net.sf.anathema.graph.util;

public class BarycenterCalculator {

  public static Double calculateVectorCenter(boolean[] vector) {
    int truePositionSum = 0;
    int numberOfTrues = 0;
    for (int index = 0; index < vector.length; index++) {
      if (vector[index]) {
        truePositionSum += index + 1;
        numberOfTrues++;
      }
    }
    if (numberOfTrues == 0) {
      return null;
    }
    return (double) truePositionSum / numberOfTrues;
  }
}