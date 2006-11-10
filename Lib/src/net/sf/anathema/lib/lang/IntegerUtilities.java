package net.sf.anathema.lib.lang;

import java.util.List;

public class IntegerUtilities {

  public static boolean isNullOrZero(Integer integer) {
    return integer == null || integer.intValue() == 0;
  }

  public static boolean isEven(int number) {
    return number % 2 == 0;
  }

  public static boolean isOdd(int number) {
    return !isEven(number);
  }

  public static int[] revert(int[] numberArray) {
    int[] revertedArray = new int[numberArray.length];
    for (int index = 0; index < numberArray.length; index++) {
      revertedArray[index] = numberArray[numberArray.length - 1 - index];
    }
    return revertedArray;
  }

  public static int[] permutate(int[] numberArray) {
    if (IntegerUtilities.isEven(numberArray.length) && numberArray.length > 2) {
      int[] permutatedIndices = new int[numberArray.length];
      int indexMiddle = numberArray.length / 2;
      for (int index = 0; index < indexMiddle; index++) {
        permutatedIndices[2 * index] = numberArray[indexMiddle - 1 - index];
        permutatedIndices[2 * index + 1] = numberArray[numberArray.length - 1 - index];
      }
      return permutatedIndices;
    }
    if (numberArray.length == 3) {
      return new int[] { numberArray[2], numberArray[0], numberArray[1] };
    }
    return revert(numberArray);
  }

  public static int[] toIntArray(List<Integer> values) {
    int[] intValues = new int[values.size()];
    for (int index = 0; index < values.size(); index++) {
      intValues[index] = values.get(index);
    }
    return intValues;
  }
}