package net.sf.anathema.charmentry.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class DurationPageProperties {

  private final IResources resources;
  private final IBasicMessage defaultMessage = new BasicMessage("Enter the Charm's Duration.");

  public DurationPageProperties(IResources resources) {
    this.resources = resources;
  }

  public IBasicMessage getBasicMessage() {
    return defaultMessage;
  }

  public String getDurationPageTitle() {
    return "Duration";
  }

  public String getDurationLabel() {
    return "Duration";
  }

  public String getInstantString() {
    return "Instant";
  }

  public String getSimpleDurationString() {
    return "Simple duration:";
  }

  public String getUntilString() {
    return "Until";
  }

  public String getQualifiedAmountDurationString() {
    return "Qualified amount duration";
  }

  public String getAbilityString() {
    return "Ability";
  }

  public String getAttributeString() {
    return "Attribute";
  }

  public String getVirtueString() {
    return "Virtue";
  }

  public String getOtherString() {
    return "Other";
  }
}
