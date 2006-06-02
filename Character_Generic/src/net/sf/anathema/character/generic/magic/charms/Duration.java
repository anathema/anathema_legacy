package net.sf.anathema.character.generic.magic.charms;

public class Duration {

  private static final String INSTANT = "Instant";//$NON-NLS-1$
  private static final String PERMANENT = "Permanent"; //$NON-NLS-1$
  public static final Duration INSTANT_DURATION = new Duration(INSTANT);
  public static final Duration PERMANENT_DURATION = new Duration(PERMANENT);

  public static Duration getDuration(String text) {
    if (text.equalsIgnoreCase(INSTANT)) {
      return INSTANT_DURATION;
    }
    if (text.equalsIgnoreCase(PERMANENT)) {
      return PERMANENT_DURATION;
    }
    return new Duration(text);
  }

  private final String text;

  private Duration(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }
}