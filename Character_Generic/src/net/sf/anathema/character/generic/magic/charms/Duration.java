package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.lib.resources.IResources;

public class Duration {

  public static final Duration INSTANT_DURATION = new Duration(DurationType.Instant, ""); //$NON-NLS-1$
  public static final Duration PERMANENT_DURATION = new Duration(DurationType.Permanent, ""); //$NON-NLS-1$

  public static Duration getDuration(String text) {
    if (text.equalsIgnoreCase("Instant")) { //$NON-NLS-1$
      return INSTANT_DURATION;
    }
    if (text.equalsIgnoreCase("Permanent")) { //$NON-NLS-1$
      return PERMANENT_DURATION;
    }
    return new Duration(DurationType.Other, text);
  }

  private final String text;
  private final DurationType type;

  private Duration(DurationType type, String text) {
    this.type = type;
    this.text = text;
  }

  public String getText(IResources resources) {
    if (type == DurationType.Instant) {
      return resources.getString("Charm.Duration.Instant"); //$NON-NLS-1$
    }
    if (type == DurationType.Permanent) {
      return resources.getString("Charm.Duration.Permanent"); //$NON-NLS-1$
    }
    return text;
  }

  public DurationType getType() {
    return type;
  }
}