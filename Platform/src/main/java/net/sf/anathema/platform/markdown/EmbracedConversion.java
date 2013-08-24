package net.sf.anathema.platform.markdown;

public class EmbracedConversion implements Conversion {

  private String delimiter;
  private String front;
  private String end;

  public EmbracedConversion(String delimiter, String tag) {
    this.delimiter = delimiter;
    this.front = "<" + tag + ">";
    this.end = "</" + tag + ">";
  }

  @Override
  public String convert(String original) {
    String text = original;
    String firstReplacement = front;
    String nextReplacement = end;
    while (text.indexOf(delimiter) >= 0) {
      int index = text.indexOf(delimiter);
      String currentReplacement = firstReplacement;
      firstReplacement = nextReplacement;
      nextReplacement = currentReplacement;
      text = text.substring(0, index) + currentReplacement + text.substring(index + delimiter.length());
    }
    return text;
  }
}
