package net.sf.anathema.lib.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import net.disy.commons.core.util.StringUtilities;

public class AnathemaStringUtilities extends StringUtilities {

  public static final String EMPTY_STRING = ""; //$NON-NLS-1$

  public static String getFileNameRepresentation(String string) {
    String fileName = ""; //$NON-NLS-1$
    for (int index = 0; index < string.length(); index++) {
      char character = string.charAt(index);
      if (Character.isJavaIdentifierPart(character)) {
        fileName += character;
      }
    }
    if (isNullOrEmpty(string)) {
      fileName = "None"; //$NON-NLS-1$
    }
    return fileName;
  }

  public static String getStringRepresentation(Object[] objects) {
    String objectString = ""; //$NON-NLS-1$
    for (int index = 0; index < objects.length; index++) {
      objectString += objects[index];
      if (index < objects.length - 1) {
        objectString += ","; //$NON-NLS-1$
      }
    }
    return "[" + objectString + "]"; //$NON-NLS-1$ //$NON-NLS-2$
  }

  public static List<Integer> allIndicesOf(String string, char character) {
    List<Integer> indexList = new ArrayList<Integer>();
    for (int index = string.indexOf(character, 0); index != -1; index = string.indexOf(character, ++index)) {
      indexList.add(index);
    }
    return indexList;
  }

  /**
   * Returns the optimal breakpoints to split a String into <code>lines</code> lines in a sorted array. If the number
   * of possible breakpoints is too low to satisfy <code>lines</code>, 0 is returned.
   */
  public static int[] findBreakPoints(String textString, int lines) {
    SortedSet<Integer> breakSet = new TreeSet<Integer>();
    breakSet.addAll(allIndicesOf(textString, ' '));
    breakSet.addAll(allIndicesOf(textString, '-'));
    List<Integer> breakList = new ArrayList<Integer>();
    for (int value : breakSet) {
      breakList.add(value + 1);
    }
    int[] lineBreaks = new int[lines - 1];
    int textLength = textString.length();
    int currentBreakPoint = 0;
    for (int searchedBreakPoint = 0; searchedBreakPoint < lines - 1; searchedBreakPoint++) {
      int lastAllowedBreakPointIndex = breakList.size() - (lines - 1 - searchedBreakPoint);
      int niceTextStartPosition = textLength / lines * (searchedBreakPoint + 1);
      while (currentBreakPoint < lastAllowedBreakPointIndex) {
        if (breakList.get(currentBreakPoint) >= niceTextStartPosition) {
          break;
        }
        currentBreakPoint++;
      }
      if (breakList.size() > currentBreakPoint) {
        lineBreaks[searchedBreakPoint] = breakList.get(currentBreakPoint);
      }
      else {
        lineBreaks[searchedBreakPoint] = 0;
      }
      currentBreakPoint++;
    }
    Arrays.sort(lineBreaks);
    return lineBreaks;
  }

  public static boolean bothNullOrEquals(Object first, Object second) {
    if (first == null) {
      return second == null;
    }
    return first.equals(second);
  }

  public static char lastCharater(String string) {
    return string.charAt(string.length() - 1);
  }

  public static String cutOffLastCharacters(String string, int characterCount) {
    return string.substring(0, string.length() - characterCount);
  }

  public static String capitalizeFirstCharacter(String string) {
    String firstCharacter = string.substring(0, 1);
    return string.replaceFirst(firstCharacter, firstCharacter.toUpperCase());
  }

  public static String concat(String[] values, String seperator) {
    StringBuffer buffer = new StringBuffer();
    for (int index = 0; index < values.length; index++) {
      buffer.append(values[index]);
      if (index < values.length - 1) {
        buffer.append(seperator);
      }
    }
    return buffer.toString();
  }
}