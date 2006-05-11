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
}
