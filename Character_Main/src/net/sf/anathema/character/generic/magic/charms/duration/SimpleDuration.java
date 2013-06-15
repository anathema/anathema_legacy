package net.sf.anathema.character.generic.magic.charms.duration;

import net.sf.anathema.lib.resources.Resources;

public class SimpleDuration implements IDuration {
  public static final String INSTANT = "Instant";
  private static final String PERMANENT = "Permanent";
  public static final SimpleDuration INSTANT_DURATION = new SimpleDuration(INSTANT);
  public static final SimpleDuration PERMANENT_DURATION = new SimpleDuration(PERMANENT);

  public static SimpleDuration getDuration(String text) {
    if (text.equalsIgnoreCase(INSTANT)) {
      return INSTANT_DURATION;
    }
    if (text.equalsIgnoreCase(PERMANENT)) {
      return PERMANENT_DURATION;
    }
    return new SimpleDuration(text);
  }

  private final String text;

  private SimpleDuration(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  @Override
  public String getText(Resources resources) {
    return resources.getString("Charm.Duration." + getText());
  }
}