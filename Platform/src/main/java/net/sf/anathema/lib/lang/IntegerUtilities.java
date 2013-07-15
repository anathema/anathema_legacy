package net.sf.anathema.lib.lang;

public class IntegerUtilities {

  public static int[] revert(int[] numberArray) {
    int[] revertedArray = new int[numberArray.length];
    for (int index = 0; index < numberArray.length; index++) {
      revertedArray[index] = numberArray[numberArray.length - 1 - index];
    }
    return revertedArray;
  }

  public static int[] permutate(int[] numberArray) {
    if (isEven(numberArray.length) && numberArray.length > 2) {
      int[] permutatedIndices = new int[numberArray.length];
      int indexMiddle = numberArray.length / 2;
      for (int index = 0; index < indexMiddle; index++) {
        permutatedIndices[2 * index] = numberArray[indexMiddle - 1 - index];
        permutatedIndices[2 * index + 1] = numberArray[numberArray.length - 1 - index];
      }
      return permutatedIndices;
    }
    if (numberArray.length == 3) {
      return new int[]{numberArray[2], numberArray[0], numberArray[1]};
    }
    return revert(numberArray);
  }

  private static boolean isEven(int number) {
    return number % 2 == 0;
  }
}