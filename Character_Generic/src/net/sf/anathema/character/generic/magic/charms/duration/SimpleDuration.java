package net.sf.anathema.character.generic.magic.charms.duration;

import net.sf.anathema.lib.resources.IStringResourceHandler;

public class SimpleDuration implements IDuration {
  public static final String INSTANT = "Instant";//$NON-NLS-1$
  private static final String PERMANENT = "Permanent"; //$NON-NLS-1$
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

  public String getText(IStringResourceHandler resources) {
    return resources.getString("Charm.Duration." + getText()); //$NON-NLS-1$
  }

  public void accept(IDurationVisitor visitor) {
    visitor.visitSimpleDuration(this);
  }
}