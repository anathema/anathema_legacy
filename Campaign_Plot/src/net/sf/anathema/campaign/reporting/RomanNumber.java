package net.sf.anathema.campaign.reporting;

public class RomanNumber {
  private final static int[] ROMAN_VALUE = new int[] { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000 };
  private final static String[] ROMAN_STRING = new String[] {
      "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$ //$NON-NLS-11$ //$NON-NLS-12$ //$NON-NLS-13$

  private final String roman;

  public RomanNumber(int arabic) {
    this.roman = calculateRomanValue(arabic);
  }

  private String calculateRomanValue(int arabic) {
    StringBuilder result = new StringBuilder();
    int remainingValue = arabic;
    for (int index = ROMAN_VALUE.length - 1; index >= 0; index--) {
      while (ROMAN_VALUE[index] <= remainingValue) {
        result.append(ROMAN_STRING[index]);
        remainingValue -= ROMAN_VALUE[index];
      }
    }
    return result.toString();
  }

  public String getRoman() {
    return roman;
  }

  public String getLowerCaseRoman() {
    return roman.toLowerCase();
  }
}