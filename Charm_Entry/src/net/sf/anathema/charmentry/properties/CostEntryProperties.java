package net.sf.anathema.charmentry.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class CostEntryProperties {

  private final IResources resources;
  private IBasicMessage defaultMessage = new BasicMessage("Enter the Charm's cost, if any.");

  public CostEntryProperties(IResources resources) {
    this.resources = resources;
  }

  public IBasicMessage getCostMessage() {
    return defaultMessage;
  }

  public String getCostTitle() {
    return "Cost";
  }

  public String getCostLabel() {
    return "Cost";
  }

  public String getTextLabel() {
    return "Additional Text";
  }

  public String getEssenceLabel() {
    return "Essence";
  }

  public String getExperienceLabel() {
    return "Experience Points";
  }

  public String getHealthLabel() {
    return "Health Levels";
  }

  public String getWillpowerLabel() {
    return "Willpower";
  }
}