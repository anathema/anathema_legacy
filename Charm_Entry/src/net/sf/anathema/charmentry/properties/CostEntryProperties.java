package net.sf.anathema.charmentry.properties;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.sf.anathema.lib.resources.IResources;

public class CostEntryProperties {

  private final IResources resources;
  private final IBasicMessage defaultMessage;

  public CostEntryProperties(IResources resources) {
    this.resources = resources;
    defaultMessage = new BasicMessage(resources.getString("CharmEntry.Cost.Message.Default")); //$NON-NLS-1$
  }

  public IBasicMessage getCostMessage() {
    return defaultMessage;
  }

  public String getCostTitle() {
    return resources.getString("CharmEntry.Cost.Title"); //$NON-NLS-1$
  }

  public String getCostLabel() {
    return resources.getString("CharmEntry.Cost.Cost"); //$NON-NLS-1$
  }

  public String getTextLabel() {
    return resources.getString("CharmEntry.Cost.AdditionalText"); //$NON-NLS-1$
  }

  public String getEssenceLabel() {
    return resources.getString("CharmEntry.Cost.Essence"); //$NON-NLS-1$;
  }

  public String getExperienceLabel() {
    return resources.getString("CharmEntry.Cost.XP"); //$NON-NLS-1$
  }

  public String getHealthLabel() {
    return resources.getString("CharmEntry.Cost.HL"); //$NON-NLS-1$
  }

  public String getWillpowerLabel() {
    return resources.getString("CharmEntry.Cost.WP"); //$NON-NLS-1$
  }
}